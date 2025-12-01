package com.hamedTech.billing.service;

import com.hamedTech.billing.dto.CategoryRequest;
import com.hamedTech.billing.dto.CategoryResponse;

import java.util.List;

public interface ICategoryService {

    CategoryResponse addCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> getAllCategories();

    void deleteCategory(String categoryId);

}
