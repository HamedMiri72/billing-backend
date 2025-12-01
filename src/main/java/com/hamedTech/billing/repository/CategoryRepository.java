package com.hamedTech.billing.repository;

import com.hamedTech.billing.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
