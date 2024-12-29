//package com.example.test.services;
//
//import com.example.test.models.Categories;
//import com.example.test.respositories.CategoryRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CategoryService {
//
//    private CategoryRepo categoryRepo;
//    @Autowired
//    public CategoryService(CategoryRepo categoryRepo) {
//        this.categoryRepo = categoryRepo;
//    }
//
//    @Transactional
//    public List<Categories>getAllCategory(){
//        return categoryRepo.findAll();
//    }
//    @Transactional
//    public Optional<Categories> getByIdCategory(Long id){
//        return categoryRepo.findById(id);
//    }
//    @Transactional
//    public Categories saveCategory(Categories categories) {
//        if (categories.getCategory_id() == null) {
//            categories.setCreated_at(LocalDateTime.now());
//        }
//        return categoryRepo.save(categories);
//    }
//    @Transactional
//    public void DeleteCategorybyId(Long id){
//        categoryRepo.deleteById(id);
//    }
//}
