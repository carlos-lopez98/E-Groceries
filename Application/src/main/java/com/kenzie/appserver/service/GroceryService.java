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

    private GroceryRepository groceryRepository;
    private CacheStore cache;

    @Autowired
    public GroceryService(GroceryRepository groceryRepository, CacheStore cache) {
        this.groceryRepository = groceryRepository;
        this.cache = cache;
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

        return item;
    }

    //U4 - Get Single Product
    public GroceryItem findByItemId(String groceryId){
        Optional<GroceryItemRecord> groceryRecordOptional = groceryRepository.findById(groceryId);

        if(groceryRecordOptional.isPresent()){
            GroceryItemRecord itemRecord = groceryRecordOptional.get();
            return new GroceryItem(itemRecord.getId(),
                    itemRecord.getName(),
                    itemRecord.getDepartment(),
                    itemRecord.getPrice(),
                    itemRecord.getExpiration(),
                    itemRecord.getType(),
                    itemRecord.getInStock(),
                    itemRecord.getQuantityAvailable(),
                    itemRecord.getDiscount());
        }else{
            return null;
        }
    }

    //U4 - Get All Products
    public List<GroceryItem> findAllItems() {
        List<GroceryItem> productList = new ArrayList<>();

        groceryRepository.findAll()
                .forEach(groceryItem -> productList.add(new GroceryItem(groceryItem.getId(), groceryItem.getName(),groceryItem.getDepartment(),groceryItem.getPrice(),
                        groceryItem.getExpiration(),groceryItem.getType(),groceryItem.getInStock(),groceryItem.getQuantityAvailable(),groceryItem.getDiscount()));
                return productList;

    }


    //U2 - Update Product
    public void updateItem(GroceryItem item) {

        if (groceryRepository.existsById(item.getGroceryProductId())) {
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
            cache.evict(groceryItemRecord.getId());
        }
    }

    //delete grocery item
    public void deleteGroceryItem(String groceryProductId){
        groceryRepository.deleteById(groceryProductId);
        cache.evict(groceryProductId);
    }
}
