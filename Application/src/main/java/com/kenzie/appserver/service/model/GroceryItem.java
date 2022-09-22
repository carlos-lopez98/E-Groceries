package com.kenzie.appserver.service.model;

import java.util.Date;
import java.util.Objects;

public class GroceryItem {
    private String groceryProductId;
    private String groceryProductName;
    private String groceryProductDepartment;
    private Double groceryProductPrice;
    private Date groceryExpirationDate;
    private String groceryType;
    private Boolean inStock;
    private Integer quantityAvailable;
    private Boolean discount ;


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
    public Boolean getDiscount() {return discount;}
    public void setDiscount(Boolean discount) {this.discount = discount;}

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
    public int hashCode(){return Objects.hash(groceryProductId, groceryProductName,groceryProductDepartment, groceryType);}

}
