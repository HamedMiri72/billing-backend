package com.hamedTech.billing.service.serviceImpl;

import com.hamedTech.billing.dto.CategoryRequest;
import com.hamedTech.billing.dto.CategoryResponse;
import com.hamedTech.billing.entity.Category;
import com.hamedTech.billing.exception.ResourceNotFoundException;
import com.hamedTech.billing.mapper.CategoryMapper;
import com.hamedTech.billing.repository.CategoryRepository;
import com.hamedTech.billing.service.ICategoryService;
import com.hamedTech.billing.service.IFileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final IFileUploadService iFileUploadService;

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest, MultipartFile file) {

        String imgUrl = iFileUploadService.uploadFile(file);
        Category newCategory = CategoryMapper.toCategoryEntity(categoryRequest);
        newCategory.setImgUrl(imgUrl);

        categoryRepository.save(newCategory);
        CategoryResponse response = CategoryMapper.toCategoryResponse(newCategory);

        return response;
    }

    @Override
    public List<CategoryResponse> getAllCategories() {


        List<Category> categories = categoryRepository.findAll();

        List<CategoryResponse> responses = categories
                .stream()
                .map(category -> CategoryMapper.toCategoryResponse(category))
                .toList();
        return responses;
    }

    @Override
    public void deleteCategory(String categoryId) {

        Category category = categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category not found with the provided id:: " + categoryId));

        iFileUploadService.deleteFile(category.getImgUrl());

        categoryRepository.delete(category);
    }

}
