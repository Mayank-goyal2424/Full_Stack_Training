package com.restfulapi.restful.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfulapi.restful.controller.entities.Products;
import com.restfulapi.restful.dao.DaoRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private DaoRepository productDao;
	
	//List<Products>list;
	

	public ProductServiceImpl() {
//		list = new ArrayList();
//		list.add(new Products(10,"Personal Loan",50000,18,7.5,true,"Mayank","09-09-2021","Mayank Goyal","10-09-2021"));
//		list.add(new Products(11,"Bussiness Loan",60000,24,12,true,"Nikhil","09-09-2021","Divakar","10-09-2021"));
//		
	}


	@Override
	public List<Products> getProduct() {
		return productDao.findAll();
//		// TODO Auto-generated method stub
//		return list;
	}
	@Override
	public Products getProduct(int productId) {
		return productDao.getOne(productId);
//		Products p=null;
//		for(Products product:list ) {
//			if(product.getProductId()==productId) {
//				p=product;
//				break;
//			}
//		}
//		return p;
	}


	@Override
	public Products addProduct(Products product) {
		productDao.save(product);
		return product;
//		// TODO Auto-generated method stub
//		list.add(product);
//		return product;
	}


	@Override
	public Products updateProduct(Products product) {
		// TODO Auto-generated method stub
		productDao.save(product);
		return product;
	}


	@Override
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		Products p=productDao.getOne(productId);
		productDao.delete(p);
	}
}
