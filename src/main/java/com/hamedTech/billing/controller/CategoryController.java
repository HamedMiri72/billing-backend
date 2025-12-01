package com.hamedTech.billing.controller;


import com.hamedTech.billing.dto.CategoryRequest;
import com.hamedTech.billing.dto.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {


    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CategoryRequest categoryRequest){

        return null;

    }
}
