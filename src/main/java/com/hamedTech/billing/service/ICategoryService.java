package com.hamedTech.billing.service;

import com.hamedTech.billing.dto.CategoryRequest;
import com.hamedTech.billing.dto.CategoryResponse;

public interface ICategoryService {

    CategoryResponse addCategory(CategoryRequest categoryRequest);

}
