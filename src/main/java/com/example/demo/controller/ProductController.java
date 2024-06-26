package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService; // Autowire Service class

	/*
	 * @GetMapping public List<Product> getAllCategories() { return
	 * productService.getAllProduct(); }
	 */
    
    @GetMapping("/products")
    public Page<Product> getProductsByPage(@RequestParam("page") int pageNumber) {
        return productService.getProductsByPage(pageNumber);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product ) {
    	Product productCategory = productService.createProduct(product);
        return new ResponseEntity<>(productCategory, HttpStatus.CREATED);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long id) {
    	Product product = productService.getProductById(id);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long id,
                                    @RequestBody Product productDetails) {
    	Product updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long id) {
        return productService.deleteProduct(id);
    }
}


