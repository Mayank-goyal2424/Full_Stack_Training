package com.restfulapi.restful.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.restfulapi.restful.controller.entities.Products;

public interface  DaoRepository extends JpaRepository<Products,Integer>,PagingAndSortingRepository<Products,Integer> {

}
