package com.hamedTech.billing.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamedTech.billing.dto.CategoryRequest;
import com.hamedTech.billing.dto.CategoryResponse;
import com.hamedTech.billing.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService iCategoryService;

    @PostMapping("/admin/categories/create")
    public ResponseEntity<CategoryResponse> addCategory(@RequestPart("category") String categoryString,
                                                        @RequestPart("file") MultipartFile file){

        ObjectMapper objectMapper = new ObjectMapper();
        CategoryRequest request = null;

        try{
            request = objectMapper.readValue(categoryString, CategoryRequest.class);
            CategoryResponse response = iCategoryService.addCategory(request, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "exception happend while parsing json in create category" + ex.getMessage());
        }

    }


    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){

        List<CategoryResponse> responses = iCategoryService.getAllCategories();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responses);
    }

    @DeleteMapping("/admin/categories/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable String categoryId){

        try{
            iCategoryService.deleteCategory(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body("user has been deleted ::" + categoryId);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }


}
