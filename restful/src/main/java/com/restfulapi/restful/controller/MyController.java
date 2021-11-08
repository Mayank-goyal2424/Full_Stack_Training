package com.restfulapi.restful.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restfulapi.restful.controller.entities.Products;
import com.restfulapi.restful.dao.DaoRepository;
import com.restfulapi.restful.exception.GlobalExceptionHandler;
import com.restfulapi.restful.exception.ResourceNotFoundException;
import com.restfulapi.restful.services.ProductService;
import com.restfulapi.restful.services.ProductServiceImpl;

@RestController
@RequestMapping("api/")
public class MyController {
	 @Autowired
     private  DaoRepository productDao;
	//private ProductServiceImpl productService;
	 @GetMapping("/productsPage")
	 public  List<Products> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy)
	    {
	        Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
	 
	        Page<Products> pagedResult = productDao.findAll(paging);
	         
	        if(pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<Products>();
	        }
	    }
	
	@GetMapping("/home")
	public String home() {
		return "this is home page";
	}
	
	// get the products
//	@Cacheable(value="Invoice")
	@GetMapping("/products")
	public List<Products> getProducts(){
		return productDao.findAll();
	}
//	@Cacheable 
	@GetMapping("/products/{product_id}")
	public ResponseEntity<Products> getProductById(@PathVariable(value = "product_id") int product_id)
			throws ResourceNotFoundException {
		Products loan = productDao.findById(product_id)
				.orElseThrow(() -> new ResourceNotFoundException("product not found for the id :- " + product_id));
		return ResponseEntity.ok().body(loan);
	}
	
	@PostMapping("/products")// new data create
	public Products createProduct(@Valid @RequestBody Products product)throws Exception{
		product.setCreatedOn();
		
		//String e=" error";
		//product.setUpdatedOn();
		try {
		productDao.save(product);
		throw new Exception("Data can't be created"+404);
		}catch(Exception e) {
			System.out.println(e);
		}
		   
	
		
		return product;
	}
//	@CachePut
	@PutMapping("/products/{productId}")// update data
	public Products updateLoan(@PathVariable(value = "productId") Integer productId,
			@RequestBody Products loanDetails) throws ResourceNotFoundException {
//		Products loan = productDao.findById(productId)
//				.orElseThrow(() -> new ResourceNotFoundException("product not found for the id :- " + productId));
//
//		loan.setProductName(loanDetails.getProductName());
//		loan.setPrincipalAmount(loanDetails.getPrincipalAmount());
//		loan.setTenure(loanDetails.getTenure());
//		loan.setRateOfInterest(loanDetails.getRateOfInterest());
//		loan.setActive(loanDetails.isActive());
//		loan.setUpdatedBy(loanDetails.getUpdatedBy());
//		loan.setUpdatedOn();
//
//		 Products updatedLoan = productDao.save(loan);
//		return ResponseEntity.ok(updatedLoan);
		return productDao.findById(productId).map(loan->{
			loan.setProductName(loanDetails.getProductName());
			loan.setPrincipalAmount(loanDetails.getPrincipalAmount());
			loan.setTenure(loanDetails.getTenure());
			loan.setRateOfInterest(loanDetails.getRateOfInterest());
			loan.setActive(loanDetails.isActive());
			loan.setUpdatedBy(loanDetails.getUpdatedBy());
			loan.setUpdatedOn();
			return productDao.save(loan);
		}).orElseThrow(() -> new ResourceNotFoundException("product not found for the id :- " + productId));
		
	}
	
	//@DeleteMapping("/products/{productId}")
//	@CacheEvict  //Delete api 
	@RequestMapping(value="/products/{productId}",method=RequestMethod.DELETE)
	public String deleteLoan(@PathVariable (value = "productId") Integer productId)
			throws ResourceNotFoundException {
		Products loan = productDao.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product not found for the id :- " + productId));

		productDao.delete(loan);
		
		return "Deleted Successfully";
	}
//	@Cacheable
	@GetMapping("/products/emi/{producId}")
	public int getMul(@PathVariable(value = "producId") Integer producId) throws ResourceNotFoundException {
		Products loan = productDao.findById(producId)
				.orElseThrow(() -> new ResourceNotFoundException("product not found for the id :- " + producId));

		double r = (loan.getRateOfInterest() / 1200);
		double emi;
		emi = loan.getPrincipalAmount() * r * (Math.pow(1 + r, loan.getTenure()))
				/ (Math.pow(1 + r, loan.getTenure()) - 1);
		return (int) Math.round(emi);

	}

		
		

	
	
}
