package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class GroceryItemCreateRequest {

    @NotEmpty
    @JsonProperty("groceryProductName")
    private String groceryProductName;

    @JsonProperty("groceryProductDepartment")
    private String groceryProductDepartment;

    @JsonProperty("groceryProductPrice")
    private Double groceryProductPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("groceryExpirationDate")
    private Date groceryExpirationDate;

    @JsonProperty("groceryType")
    private String groceryType;

    @JsonProperty("inStock")
    private Boolean inStock;

    @JsonProperty("quantityAvailable")
    private Integer quantityAvailable;

    @JsonProperty("discount")
    private Boolean discount;

    public String getGroceryProductName() {
        return groceryProductName;
    }

    public String getGroceryProductDepartment() {
        return groceryProductDepartment;
    }

    public Double getGroceryProductPrice() {
        return groceryProductPrice;
    }

    public Date getGroceryExpirationDate() {
        return groceryExpirationDate;
    }

    public String getGroceryType() {
        return groceryType;
    }

    public Boolean getInStock() {
        return inStock;
    }
    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public Boolean getDiscount() {
        return discount;
    }

    public void setGroceryProductName(String groceryProductName) {
        this.groceryProductName = groceryProductName;
    }

    public void setGroceryProductDepartment(String groceryProductDepartment) {
        this.groceryProductDepartment = groceryProductDepartment;
    }

    public void setGroceryProductPrice(Double groceryProductPrice) {
        this.groceryProductPrice = groceryProductPrice;
    }

    public void setGroceryExpirationDate(Date groceryExpirationDate) {
        this.groceryExpirationDate = groceryExpirationDate;
    }

    public void setGroceryType(String groceryType) {
        this.groceryType = groceryType;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public void setDiscount(Boolean discount) {
        this.discount = discount;
    }
}
