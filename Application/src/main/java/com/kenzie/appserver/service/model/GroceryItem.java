package com.kenzie.appserver.service.model;


import java.util.Date;
import java.util.Objects;

public class GroceryItem {
    private final String groceryProductId;
    private final String groceryProductName;
    private final String groceryProductDepartment;
    private final Double groceryProductPrice;
    private final Date groceryExpirationDate;
    private final String groceryType;
    private final Boolean inStock;
    private final Integer quantityAvailable;
    private final Boolean discount ;

    public GroceryItem (String groceryProductId, String groceryProductName, String groceryProductDepartment,
                        Double groceryProductPrice, Date groceryExpirationDate, String groceryType, Boolean inStock,
                        Integer quantityAvailable, Boolean discount) {
        this.groceryProductId = groceryProductId;
        this.groceryProductName = groceryProductName;
        this.groceryProductDepartment = groceryProductDepartment;
        this.groceryProductPrice = groceryProductPrice;
        this.groceryExpirationDate = groceryExpirationDate;
        this.groceryType = groceryType;
        this.inStock = inStock;
        this.quantityAvailable = quantityAvailable;
        this.discount = discount;
    }

    public String getGroceryProductId() {
        return groceryProductId;
    }

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

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(o==null || getClass() != o.getClass()) return false;

        GroceryItem that = (GroceryItem) o;
        return groceryProductId.equals(that.groceryProductId) && Objects.equals(groceryProductName, that.groceryProductName) &&
                Objects.equals(groceryProductDepartment, that.groceryProductDepartment)
                && Objects.equals(groceryType, that.groceryType);
    }

    @Override
    public int hashCode(){
        return Objects.hash(groceryProductId, groceryProductName,groceryProductDepartment, groceryType);
    }
}
