package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kenzie.appserver.service.model.GroceryItem;

import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroceryItemResponse {

    @JsonProperty
    private String groceryProductId;
    @JsonProperty
    private String groceryProductName;
    @JsonProperty
    private String groceryProductDepartment;
    @JsonProperty
    private Double groceryProductPrice;
    @JsonProperty
    private Date groceryExpirationDate;
    @JsonProperty
    private String groceryType;
    @JsonProperty
    private Boolean inStock;
    @JsonProperty
    private Integer quantityAvailable;
    @JsonProperty
    private Boolean discount;


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
    public Double getGroceryProductPrice() {return groceryProductPrice;}
    public void setGroceryProductPrice(Double groceryProductPrice) {this.groceryProductPrice = groceryProductPrice;}
    public Date getGroceryExpirationDate() {return groceryExpirationDate;}
    public void setGroceryExpirationDate(Date groceryExpirationDate) {this.groceryExpirationDate = groceryExpirationDate;}
    public String getGroceryType() {return groceryType;}
    public void setGroceryType(String groceryType) {this.groceryType = groceryType;}
    public Boolean getInStock() {return inStock;}
    public void setInStock(Boolean inStock) {this.inStock = inStock;}
    public Integer getQuantityAvailable() {return quantityAvailable;}
    public void setQuantityAvailable(Integer quantityAvailable) {this.quantityAvailable = quantityAvailable;}
    public Boolean getDiscount() {return discount;}public void setDiscount(Boolean discount) {this.discount = discount;}
}
