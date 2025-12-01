package com.hamedTech.billing.service.serviceImpl;

import com.hamedTech.billing.dto.CategoryRequest;
import com.hamedTech.billing.dto.CategoryResponse;
import com.hamedTech.billing.entity.Category;
import com.hamedTech.billing.exception.ResourceNotFoundException;
import com.hamedTech.billing.mapper.CategoryMapper;
import com.hamedTech.billing.repository.CategoryRepository;
import com.hamedTech.billing.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {

        Category newCategory = CategoryMapper.toCategoryEntity(categoryRequest);
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

        categoryRepository.delete(category);
    }

}
