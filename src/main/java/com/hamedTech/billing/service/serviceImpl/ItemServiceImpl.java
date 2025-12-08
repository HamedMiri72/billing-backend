package com.hamedTech.billing.service.serviceImpl;

import com.hamedTech.billing.dto.item.ItemRequest;
import com.hamedTech.billing.dto.item.ItemResponse;
import com.hamedTech.billing.entity.Category;
import com.hamedTech.billing.entity.Item;
import com.hamedTech.billing.exception.ResourceNotFoundException;
import com.hamedTech.billing.mapper.ItemMapper;
import com.hamedTech.billing.repository.CategoryRepository;
import com.hamedTech.billing.repository.ItemRepository;
import com.hamedTech.billing.service.IFileUploadService;
import com.hamedTech.billing.service.IItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements IItemService {


    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final IFileUploadService iFileUploadService;

    @Override
    public ItemResponse createItem(MultipartFile file, ItemRequest request) {

        String imgUrl = iFileUploadService.uploadFile(file);

        Item item = ItemMapper.toItem(request);
        item.setImgUrl(imgUrl);

        Category category = categoryRepository.findByCategoryId(request.getCategoryId())
                .orElseThrow(() -> new  ResourceNotFoundException("category is not found with provided id::" + request.getCategoryId()));

        item.setCategory(category);
        itemRepository.save(item);

        ItemResponse response = ItemMapper.toItemResponse(item);


        return response;
    }

    @Override
    public List<ItemResponse> getAllItems() {

        List<Item> items = itemRepository.findAll();

        List<ItemResponse> responses = items.stream()
                .map(item -> ItemMapper.toItemResponse(item))
                .toList();



        return responses;

    }

    @Override
    public void deleteItem(String itemId) {

        Item item = itemRepository.findByItemId(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("item is not found" + itemId));

        iFileUploadService.deleteFile(item.getImgUrl());
        itemRepository.delete(item);

    }
}
