package com.restfulapi.restful.services;

import java.util.List;

import com.restfulapi.restful.controller.entities.Products;

public interface ProductService {
	
	public List<Products> getProduct();
	public Products getProduct(int productId);
    public Products addProduct(Products product);
    public Products updateProduct(Products product);
    public void deleteProduct(int productId);
}
