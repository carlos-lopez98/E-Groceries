package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.model.GroceryItemResponse;
import com.kenzie.appserver.repositories.GroceryRepository;
import com.kenzie.appserver.repositories.model.GroceryItemRecord;
import com.kenzie.appserver.service.model.GroceryItem;
import com.kenzie.appserver.config.CacheStore;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroceryService {

    @Autowired
    private GroceryRepository groceryRepository;
    private CacheStore cache;

    public GroceryService(GroceryRepository groceryRepository, CacheStore cacheStore) {
        this.groceryRepository = groceryRepository;
        this.cache = cacheStore;
    }

    //U2 - Add New Product
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
        cache.add(item.getGroceryProductName(), item);

        return item;
    }

    //U4 - Get Single Product
    public GroceryItem findByItemName(String name){
       GroceryItem cachedGroceryItem = cache.get(name);

       //Check if grocery item is cached and return it if true
       if (cachedGroceryItem != null){
           return cachedGroceryItem;
       }

       GroceryItem groceryItemFromBackend = groceryRepository
               .findById(name)
               .map(itemRecord -> new GroceryItem(itemRecord.getId(),
                       itemRecord.getName(),
                       itemRecord.getDepartment(),
                       itemRecord.getPrice(),
                       itemRecord.getExpiration(),
                       itemRecord.getType(),
                       itemRecord.getInStock(),
                       itemRecord.getQuantityAvailable(),
                       itemRecord.getDiscount()))
               .orElse(null);

       if (groceryItemFromBackend != null){
           cache.add(groceryItemFromBackend.getGroceryProductName(), groceryItemFromBackend);
       }

       return groceryItemFromBackend;
    }

    //U4 - Get All Products
    public List<GroceryItem> findAllItems() {
        List<GroceryItem> productList = new ArrayList<>();

        groceryRepository.findAll()
                .forEach(groceryItem -> productList.add(new GroceryItem(groceryItem.getId(), groceryItem.getName(),groceryItem.getDepartment(),groceryItem.getPrice(),
                        groceryItem.getExpiration(),groceryItem.getType(),groceryItem.getInStock(),groceryItem.getQuantityAvailable(),groceryItem.getDiscount())));
                return productList;

    }

    //U2 - Update Product
    public void updateItem(GroceryItem item) {

        if (groceryRepository.existsById(item.getGroceryProductName())) {
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
            cache.evict(item.getGroceryProductName());
        }
    }

    //delete grocery item
    public void deleteGroceryItem(String groceryProductName){
        groceryRepository.deleteById(groceryProductName);
        cache.evict(groceryProductName);
    }
}
