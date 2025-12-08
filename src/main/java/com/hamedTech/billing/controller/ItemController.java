package com.hamedTech.billing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamedTech.billing.dto.item.ItemRequest;
import com.hamedTech.billing.dto.item.ItemResponse;
import com.hamedTech.billing.entity.Item;
import com.hamedTech.billing.exception.ResourceNotFoundException;
import com.hamedTech.billing.service.IItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {


    private final IItemService iItemService;



    @PostMapping("/admin/items/create")
    public ResponseEntity<ItemResponse> createItem(@RequestPart("file") MultipartFile file,
                                                   @RequestPart("item") String itemRequest) throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();

        ItemRequest request = null;

        try{
            request = objectMapper.readValue(itemRequest, ItemRequest.class);
            ItemResponse response = iItemService.createItem(file, request);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        }catch (Exception ex){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }


    }



    @GetMapping("/items")
    public ResponseEntity<List<ItemResponse>> getAllItems(){

        List<ItemResponse> response = iItemService.getAllItems();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/admin/items/delete/{itemId}")
    public void deleteItem(@PathVariable String itemId){

        try{
            iItemService.deleteItem(itemId);

        }catch (Exception ex){
            throw new ResourceNotFoundException("item is not found in the database");
        }
    }
}
