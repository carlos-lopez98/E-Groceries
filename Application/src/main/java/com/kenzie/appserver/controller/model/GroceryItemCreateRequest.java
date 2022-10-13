package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class GroceryItemCreateRequest {

    @JsonProperty("groceryProductId")
    private String groceryProductId;

    @NotEmpty
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

    public String getGroceryProductId() {
        return groceryProductId;
    }

    public String getGroceryProductName() {
        return groceryProductName;
    }

    public String getGroceryProductDepartment() {
        return groceryProductDepartment;
    }

    public double getGroceryProductPrice() {
        return groceryProductPrice;
    }

    public String getGroceryExpirationDate() {
        return groceryExpirationDate;
    }

    public String getGroceryType() {
        return groceryType;
    }

    public boolean getInStock() {
        return inStock;
    }
    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public boolean getDiscount() {
        return discount;
    }

    public void setGroceryProductName(String groceryProductName) {
        this.groceryProductName = groceryProductName;
    }

    public void setGroceryProductDepartment(String groceryProductDepartment) {
        this.groceryProductDepartment = groceryProductDepartment;
    }

    public void setGroceryProductPrice(double groceryProductPrice) {
        this.groceryProductPrice = groceryProductPrice;
    }

    public void setGroceryExpirationDate(String groceryExpirationDate) {
        this.groceryExpirationDate = groceryExpirationDate;
    }

    public void setGroceryType(String groceryType) {
        this.groceryType = groceryType;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public void setGroceryProductId(String groceryProductId) {
        this.groceryProductId = groceryProductId;
    }
}
