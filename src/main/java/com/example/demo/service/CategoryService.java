package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import com.example.demo.ExceptionHandling;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;

@org.springframework.stereotype.Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    
    public Page<Category> getCateogriesByPage(int pageNumber) {
        int pageSize = 10; // Number of products per page
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return categoryRepository.findAll(pageRequest);
    }


    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new ExceptionHandling("Category", "id", id));
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = getCategoryById(id);
        category.setName(categoryDetails.getName());
        return categoryRepository.save(category);
    }

    public ResponseEntity<?> deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
        return ResponseEntity.ok().build();
    }
}




