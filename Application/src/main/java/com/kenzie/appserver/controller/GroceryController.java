package com.kenzie.appserver.controller;
import com.kenzie.appserver.controller.model.GroceryItemCreateRequest;
import com.kenzie.appserver.controller.model.GroceryItemResponse;
import com.kenzie.appserver.controller.model.GroceryItemUpdateRequest;
import com.kenzie.appserver.service.GroceryService;
import com.kenzie.appserver.service.model.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/grocery-item")
public class GroceryController {

    @Autowired
    private GroceryService groceryService;

    GroceryController (GroceryService groceryService){
        this.groceryService = groceryService;
    }


    //U4 - Get Single product
    @GetMapping("/{name}")
    public ResponseEntity<GroceryItemResponse> getGroceryItem(@PathVariable("name") String name) {
        GroceryItem groceryItem = groceryService.findByItemName(name);

        if (groceryItem == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(createGroceryItemResponse(groceryItem));
    }

    //U4 - Get All Products
    @GetMapping("/all")
    public ResponseEntity<List<GroceryItemResponse>> getAllGroceryItems()   {

        List<GroceryItem> productList = groceryService.findAllItems();

        List<GroceryItemResponse> allProductsResponse = productList.stream().map(groceryItem ->
                createGroceryItemResponse(groceryItem)).collect(Collectors.toList());

        return ResponseEntity.ok(allProductsResponse);

    }

    @PostMapping()
    public ResponseEntity<GroceryItemResponse> createGroceryItem(@RequestBody GroceryItemCreateRequest groceryItemCreateRequest) {
        GroceryItem groceryItem = new GroceryItem(randomUUID().toString(),groceryItemCreateRequest.getGroceryProductName(),
                groceryItemCreateRequest.getGroceryProductDepartment(), groceryItemCreateRequest.getGroceryProductPrice(),
                groceryItemCreateRequest.getGroceryExpirationDate(), groceryItemCreateRequest.getGroceryType(),
                groceryItemCreateRequest.getInStock(), groceryItemCreateRequest.getQuantityAvailable(),
                groceryItemCreateRequest.getDiscount());
        groceryService.addNewItem(groceryItem);

        GroceryItemResponse groceryItemResponse = createGroceryItemResponse(groceryItem);

        return ResponseEntity.created(URI.create("/grocery-item/" + groceryItemResponse.getGroceryProductName())).body(groceryItemResponse);
    }

    @PostMapping("/{name}")
    public ResponseEntity<GroceryItemResponse> updateGroceryItem(@PathVariable("name") String name, @RequestBody GroceryItemUpdateRequest groceryItemUpdateRequest) {

        GroceryItem groceryItemOld = groceryService.findByItemName(name);
        GroceryItem groceryItemNew = groceryService.findByItemName(name);

        if (groceryItemOld == null) {
            return ResponseEntity.notFound().build();
        }

        groceryItemNew.setGroceryProductId(groceryItemUpdateRequest.getGroceryProductId());
        groceryItemNew.setGroceryProductName(groceryItemUpdateRequest.getGroceryProductName());
        groceryItemNew.setGroceryProductDepartment(groceryItemUpdateRequest.getGroceryProductDepartment());
        groceryItemNew.setGroceryProductPrice(groceryItemUpdateRequest.getGroceryProductPrice());
        groceryItemNew.setGroceryExpirationDate(groceryItemUpdateRequest.getGroceryExpirationDate());
        groceryItemNew.setGroceryType(groceryItemUpdateRequest.getGroceryType());
        groceryItemNew.setInStock(groceryItemUpdateRequest.getInStock());
        groceryItemNew.setQuantityAvailable(groceryItemUpdateRequest.getQuantityAvailable());
        groceryItemNew.setDiscount(groceryItemUpdateRequest.getDiscount());

        groceryService.deleteGroceryItem(name);

        groceryService.updateItem(groceryItemNew);

        GroceryItemResponse groceryItemResponse = createGroceryItemResponse(groceryItemNew);

        return ResponseEntity.ok(groceryItemResponse);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<GroceryItemResponse> deleteGroceryItemById(@PathVariable("name") String name) {
        groceryService.deleteGroceryItem(name);
        GroceryItemResponse response = new GroceryItemResponse();
        response.setGroceryProductName(name);
        return ResponseEntity.ok(response);
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

}
