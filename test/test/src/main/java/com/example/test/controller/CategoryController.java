//package com.example.test.controller;
//
//import com.example.test.models.Categories;
//import com.example.test.services.CategoryService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@CrossOrigin(origins = { "*" })
//@RequestMapping("/api")
//public class CategoryController {
//    private CategoryService categoryService;
//    @Autowired
//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
//    @GetMapping("/category")
//    public List<Categories> getAll(){
//        return categoryService.getAllCategory();
//    }
//    @GetMapping("/category/{id}")
//    public ResponseEntity<Categories> getCategory(@PathVariable Long id) {
//        Optional<Categories> category = categoryService.getByIdCategory(id);
//        if (category.isPresent()) {
//            return new ResponseEntity<>(category.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//    @PostMapping("/category")
//    public ResponseEntity<Categories> CreateCategory(@Valid @RequestBody Categories categories) {
//        try {
//            Categories create = categoryService.saveCategory(categories);
//            return new ResponseEntity<>(create, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR)).build();
//        }
//    }
//    @DeleteMapping("/category/{id}")
//    public ResponseEntity<Void> deletePostById(@PathVariable Long id) {
//        Optional<Categories> category = categoryService.getByIdCategory(id);
//        if (category.isPresent()) {
//            categoryService.DeleteCategorybyId(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//}
