package com.hamedTech.billing.repository;

import com.hamedTech.billing.entity.Category;
import com.hamedTech.billing.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {

    Optional<Item> findByItemId(String ItemId);
//
//    Integer countByCategoryId(String categoryId);
    List<Item> findByCategory(Category category);
}
