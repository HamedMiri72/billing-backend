package com.hamedTech.billing.controller;


import com.hamedTech.billing.dto.CategoryRequest;
import com.hamedTech.billing.dto.CategoryResponse;
import com.hamedTech.billing.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService iCategoryService;


    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CategoryRequest categoryRequest){

        CategoryResponse response = iCategoryService.addCategory(categoryRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }


    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){

        List<CategoryResponse> responses = iCategoryService.getAllCategories();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responses);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String categoryId){

        try{
            iCategoryService.deleteCategory(categoryId);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
