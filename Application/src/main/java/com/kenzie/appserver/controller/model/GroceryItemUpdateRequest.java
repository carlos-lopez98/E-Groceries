package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class GroceryItemUpdateRequest {

    @NotEmpty
    @JsonProperty("groceryProductId")
    private String groceryProductId;

    @NotEmpty
    @JsonProperty("groceryProductName")
    private String groceryProductName;

    @JsonProperty("groceryProductDepartment")
    private String groceryProductDepartment;

    @Min(0)
    @JsonProperty("groceryProductPrice")
    private Double groceryProductPrice;

    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("groceryExpirationDate")
    private Date groceryExpirationDate;

    @JsonProperty("groceryType")
    private String groceryType;

    @JsonProperty("inStock")
    private Boolean inStock;

    @Min(0)
    @JsonProperty("quantityAvailable")
    private Integer quantityAvailable;

    @JsonProperty("discount")
    private Boolean discount;

    public String getGroceryProductId() {
        return groceryProductId;
    }

    public void setGroceryProductId(String groceryProductId) {
        this.groceryProductId = groceryProductId;
    }

    public String getGroceryProductName() {
        return groceryProductName;
    }

    public void setGroceryProductName(String groceryProductName) {
        this.groceryProductName = groceryProductName;
    }

    public String getGroceryProductDepartment() {
        return groceryProductDepartment;
    }

    public void setGroceryProductDepartment(String groceryProductDepartment) {
        this.groceryProductDepartment = groceryProductDepartment;
    }

    public Double getGroceryProductPrice() {
        return groceryProductPrice;
    }

    public void setGroceryProductPrice(Double groceryProductPrice) {
        this.groceryProductPrice = groceryProductPrice;
    }

    public Date getGroceryExpirationDate() {
        return groceryExpirationDate;
    }

    public void setGroceryExpirationDate(Date groceryExpirationDate) {
        this.groceryExpirationDate = groceryExpirationDate;
    }

    public String getGroceryType() {
        return groceryType;
    }

    public void setGroceryType(String groceryType) {
        this.groceryType = groceryType;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Boolean getDiscount() {
        return discount;
    }

    public void setDiscount(Boolean discount) {
        this.discount = discount;
    }
}
