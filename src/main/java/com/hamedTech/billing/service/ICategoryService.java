package com.hamedTech.billing.service;

import com.hamedTech.billing.dto.CategoryRequest;
import com.hamedTech.billing.dto.CategoryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICategoryService {

    CategoryResponse addCategory(CategoryRequest categoryRequest, MultipartFile file);

    List<CategoryResponse> getAllCategories();

    void deleteCategory(String categoryId);

}
