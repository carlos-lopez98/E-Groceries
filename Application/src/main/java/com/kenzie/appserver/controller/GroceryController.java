package com.kenzie.appserver.controller;
import com.kenzie.appserver.service.GroceryService;

public class GroceryController {
    private GroceryService groceryService;

    GroceryController (GroceryService groceryService){
        this.groceryService = groceryService;
    }

}
