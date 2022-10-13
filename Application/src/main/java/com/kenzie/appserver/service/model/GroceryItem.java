package com.kenzie.appserver.service.model;

import java.util.Objects;

public class GroceryItem {

    private String groceryProductId;
    private String groceryProductName;
    private String groceryProductDepartment;
    private double groceryProductPrice;
    private String groceryExpirationDate;
    private String groceryType;
    private boolean inStock;
    private Integer quantityAvailable;
    private boolean discount;

    public GroceryItem (String groceryProductId, String groceryProductName, String groceryProductDepartment,
                        double groceryProductPrice, String groceryExpirationDate, String groceryType,boolean inStock,
                        Integer quantityAvailable, boolean discount) {
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
