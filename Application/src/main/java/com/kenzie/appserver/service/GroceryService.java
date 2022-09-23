package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.GroceryRepository;
import com.kenzie.appserver.repositories.model.GroceryItemRecord;
import com.kenzie.appserver.service.model.GroceryItem;

import org.springframework.stereotype.Service;

@Service
public class GroceryService {

    private GroceryRepository groceryRepository;

    public GroceryService(GroceryRepository groceryRepository) {
        this.groceryRepository = groceryRepository;
    }

    public GroceryItem addNewItem(GroceryItem item) {

        GroceryItemRecord groceryItemRecord = new GroceryItemRecord();
        groceryItemRecord.setId(item.getGroceryProductId());
        groceryItemRecord.setName(item.getGroceryProductName());
        groceryItemRecord.setDepartment(item.getGroceryProductDepartment());
        groceryItemRecord.setPrice(item.getGroceryProductPrice());
        groceryItemRecord.setExpiration(item.getGroceryExpirationDate());
        groceryItemRecord.setInStock(item.getInStock());
        groceryItemRecord.setQuantityAvailable(item.getQuantityAvailable());
        groceryItemRecord.setType(item.getGroceryType());
        groceryItemRecord.setDiscount(item.getDiscount());

        groceryRepository.save(groceryItemRecord);

        return item;
    }

}