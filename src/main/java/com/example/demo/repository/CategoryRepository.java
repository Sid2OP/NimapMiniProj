package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Category;
import com.example.demo.model.Product;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Page<Category> findAll(Pageable pageable);
	
	
}
