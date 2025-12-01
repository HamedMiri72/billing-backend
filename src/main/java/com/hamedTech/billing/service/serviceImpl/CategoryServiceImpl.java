package com.hamedTech.billing.service.serviceImpl;

import com.hamedTech.billing.dto.CategoryRequest;
import com.hamedTech.billing.dto.CategoryResponse;
import com.hamedTech.billing.entity.Category;
import com.hamedTech.billing.mapper.CategoryMapper;
import com.hamedTech.billing.repository.CategoryRepository;
import com.hamedTech.billing.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
