package com.amarnath.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amarnath.productservice.model.Product;

public interface ProductRepository  extends JpaRepository<Product, Long>{


    
}
