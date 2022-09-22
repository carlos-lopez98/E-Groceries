package com.kenzie.appserver.controller;

public class GroceryController {
    private GroceryService groceryService;

    GroceryController (GroceryService groceryService){
        this.groceryService = groceryService;
    }

}
