package com.hamedTech.billing.service;

import com.hamedTech.billing.dto.item.ItemRequest;
import com.hamedTech.billing.dto.item.ItemResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IItemService {

    ItemResponse createItem(MultipartFile file, ItemRequest request);


    List<ItemResponse> getAllItems();

    void deleteItem(String itemId);
}
