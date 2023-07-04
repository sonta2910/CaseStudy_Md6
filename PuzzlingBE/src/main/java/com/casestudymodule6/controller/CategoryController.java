package com.casestudymodule6.controller;

import com.casestudymodule6.model.question.Category;
import com.casestudymodule6.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("puzzling/categories")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<Iterable<Category>> getAllCategories() {
        List<Category> categories = (List<Category>) categoryService.findAll();
        if (categories.isEmpty()) return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        return new ResponseEntity<> (categories, HttpStatus.OK);
    }
    @GetMapping("/{category}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Category category) {
        return ResponseEntity.ok(category);
    }
    @PostMapping("/")
    public ResponseEntity<Category> createNewCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> editCategory(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isEmpty()) return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        category.setId(categoryOptional.get().getId());
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isEmpty()) return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        categoryService.remove(id);
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }
}
