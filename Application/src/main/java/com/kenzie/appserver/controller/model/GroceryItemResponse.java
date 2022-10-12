package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.GroceryItem;

import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroceryItemResponse {

    @JsonProperty("groceryProductId")
    private String groceryProductId;
    @JsonProperty("groceryProductName")
    private String groceryProductName;
    @JsonProperty("groceryProductDepartment")
    private String groceryProductDepartment;
    @JsonProperty("groceryProductPrice")
    private double groceryProductPrice;
    @JsonProperty("groceryExpirationDate")
    private String groceryExpirationDate;
    @JsonProperty("groceryType")
    private String groceryType; 
    @JsonProperty("inStock")
    private boolean inStock;
    @JsonProperty("quantityAvailable")
    private Integer quantityAvailable;
    @JsonProperty("discount")
    private boolean discount;


    public GroceryItemResponse (GroceryItem groceryItem){
        this.groceryProductId = groceryItem.getGroceryProductId();
        this.groceryProductName = groceryItem.getGroceryProductName();
        this.groceryProductDepartment = groceryItem.getGroceryProductDepartment();
        this.groceryProductPrice = groceryItem.getGroceryProductPrice();
        this.groceryExpirationDate = groceryItem.getGroceryExpirationDate();
        this.groceryType = groceryItem.getGroceryType();
        this.inStock = groceryItem.getInStock();
        this.quantityAvailable = groceryItem.getQuantityAvailable();
        this.discount = groceryItem.getDiscount();
    }

    //Default Constructor
    public GroceryItemResponse(){

    }

    public String getGroceryProductId() {return groceryProductId;}
    public void setGroceryProductId(String groceryProductId) {this.groceryProductId = groceryProductId;}
    public String getGroceryProductName() {return groceryProductName;}
    public void setGroceryProductName(String groceryProductName) {this.groceryProductName = groceryProductName;}
    public String getGroceryProductDepartment() {return groceryProductDepartment;}
    public void setGroceryProductDepartment(String groceryProductDepartment) {this.groceryProductDepartment = groceryProductDepartment;}
    public double getGroceryProductPrice() {return groceryProductPrice;}
    public void setGroceryProductPrice(double groceryProductPrice) {this.groceryProductPrice = groceryProductPrice;}
    public String getGroceryExpirationDate() {return groceryExpirationDate;}
    public void setGroceryExpirationDate(String groceryExpirationDate) {this.groceryExpirationDate = groceryExpirationDate;}
    public String getGroceryType() {return groceryType;}
    public void setGroceryType(String groceryType) {this.groceryType = groceryType;}
    public boolean getInStock() {return inStock;}
    public void setInStock(boolean inStock) {this.inStock = inStock;}
    public Integer getQuantityAvailable() {return quantityAvailable;}
    public void setQuantityAvailable(Integer quantityAvailable) {this.quantityAvailable = quantityAvailable;}
    public boolean getDiscount() {return discount;}
    public void setDiscount(boolean discount) {this.discount = discount;}
}
