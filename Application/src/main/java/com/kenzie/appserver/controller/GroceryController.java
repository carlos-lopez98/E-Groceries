package com.kenzie.appserver.controller;
import com.kenzie.appserver.controller.model.GroceryItemCreateRequest;
import com.kenzie.appserver.controller.model.GroceryItemResponse;
import com.kenzie.appserver.service.GroceryService;
import com.kenzie.appserver.service.model.GroceryItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/grocery-item")
public class GroceryController {
    private GroceryService groceryService;

    GroceryController (GroceryService groceryService){
        this.groceryService = groceryService;
    }


    //U4 - Get Single product
    @GetMapping("/{id}")
    public ResponseEntity<GroceryItemResponse> getItem(@PathVariable("id") String id) {
        GroceryItem groceryItem = groceryService.findByItemId(id);
        if (groceryItem == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(createGroceryItemResponse(groceryItem));
    }

    //U4 - Get All Products
    @GetMapping("/all")
    public ResponseEntity<List<GroceryItemResponse>> getAllItems()   {

        List<GroceryItem> productList = groceryService.findAllItems();

        List<GroceryItemResponse> allProductsResponse = productList.stream().map(groceryItem -> groceryItemToResponse(groceryItem)).collect(Collectors.toList()));

        return ResponseEntity.ok(allProductsResponse);

    }


    @PostMapping
    public ResponseEntity<GroceryItemResponse> createItem(@RequestBody GroceryItemCreateRequest groceryItemCreateRequest) {
        GroceryItem groceryItem = new GroceryItem(randomUUID().toString(), groceryItemCreateRequest.getGroceryProductName(),
                groceryItemCreateRequest.getGroceryProductDepartment(), groceryItemCreateRequest.getGroceryProductPrice(),
                groceryItemCreateRequest.getGroceryExpirationDate(), groceryItemCreateRequest.getGroceryType(),
                groceryItemCreateRequest.getInStock(), groceryItemCreateRequest.getQuantityAvailable(),
                groceryItemCreateRequest.getDiscount());
        groceryService.addNewItem(groceryItem);

        GroceryItemResponse groceryItemResponse = createGroceryItemResponse(groceryItem);

        return ResponseEntity.created(URI.create("/grocery-item" + groceryItemResponse.getGroceryProductId())).body(groceryItemResponse);
    }

    private GroceryItemResponse createGroceryItemResponse(GroceryItem groceryItem) {
        GroceryItemResponse groceryItemResponse = new GroceryItemResponse();
        groceryItemResponse.setGroceryProductId(groceryItem.getGroceryProductId());
        groceryItemResponse.setGroceryProductName(groceryItem.getGroceryProductName());
        groceryItemResponse.setGroceryProductDepartment(groceryItem.getGroceryProductDepartment());
        groceryItemResponse.setGroceryProductPrice(groceryItem.getGroceryProductPrice());
        groceryItemResponse.setGroceryExpirationDate(groceryItem.getGroceryExpirationDate());
        groceryItemResponse.setGroceryType(groceryItem.getGroceryType());
        groceryItemResponse.setInStock(groceryItem.getInStock());
        groceryItemResponse.setQuantityAvailable(groceryItem.getQuantityAvailable());
        groceryItemResponse.setDiscount(groceryItem.getDiscount());
        return groceryItemResponse;
    }

    private GroceryItemResponse groceryItemToResponse(GroceryItem item){
        GroceryItemResponse groceryItemResponse = new GroceryItemResponse();
        groceryItemResponse.setGroceryProductId(item.getGroceryProductId());
        groceryItemResponse.setGroceryType(item.getGroceryType());
        groceryItemResponse.setGroceryProductName(item.getGroceryProductName());
        groceryItemResponse.setGroceryProductPrice(item.getGroceryProductPrice());
        groceryItemResponse.setGroceryExpirationDate(item.getGroceryExpirationDate());
        groceryItemResponse.setDiscount(item.getDiscount());
        groceryItemResponse.setGroceryProductDepartment(item.getGroceryProductDepartment());
        groceryItemResponse.setInStock(item.getInStock());
        groceryItemResponse.setQuantityAvailable(item.getQuantityAvailable());

        return groceryItemResponse;
    }



}
