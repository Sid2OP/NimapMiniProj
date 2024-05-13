package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.example.demo.ExceptionHandling;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@org.springframework.stereotype.Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    

    public List<Product> getAllCategories() {
        return productRepository.findAll();
    }
    
    
    public Page<Product> getProductsByPage(int pageNumber) {
        int pageSize = 10; // Number of products per page
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(pageRequest);
    }

	/*
	 * public Page<Product> getAllProduct(Integer pageNumber, Integer pageSize) {
	 * 
	 * Pageable pageable = PageRequest.of(pageNumber, pageSize);
	 * 
	 * List<Product> products = productRepository.findAll();
	 * 
	 * int startIndex = (int) pageable.getOffset(); int endIndex =
	 * Math.min(startIndex +pageable.getPageSize(), products.size());
	 * 
	 * List<Product> pageContent = products.subList(startIndex, endIndex);
	 * 
	 * Page<Product> pageProducts = new PageImpl<>(pageContent, pageable, endIndex);
	 * 
	 * return pageProducts; }
	 */
    
    public Product createProduct(Product Product) {
        return productRepository.save(Product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ExceptionHandling("Product", "id", id));
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setBrand(productDetails.getBrand());
        return productRepository.save(product);
    }

    public ResponseEntity<?> deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }
}
