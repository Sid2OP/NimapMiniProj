package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Page<Product> findAll(Pageable pageable);
	
}
