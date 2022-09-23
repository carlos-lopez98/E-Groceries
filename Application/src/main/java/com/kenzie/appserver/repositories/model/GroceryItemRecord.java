package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import java.util.Date;
import java.util.Objects;

@DynamoDBTable(tableName = "DynamoDBIndexes-Groceries")
public class GroceryItemRecord {

    private static final String GROCERY_PRICE_INDEX = "GroceryPriceIndex";
    private static final String GROCERY_EXPIRATION_INDEX = "GroceryExpirationIndex";

    private String id;
    private String name;
    private String department;
    private Double price;
    private Date expiration;
    private Boolean inStock;
    private Integer quantityAvailable;
    private String type;
    private Boolean discount;

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = GROCERY_PRICE_INDEX, attributeName = "id")
    public String getId() {return id;}

    @DynamoDBIndexHashKey(globalSecondaryIndexName = GROCERY_EXPIRATION_INDEX, attributeName = "name")
    public String getName() {return name;}

    @DynamoDBRangeKey(attributeName = "department")
    public String getDepartment() {return department;}

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = GROCERY_PRICE_INDEX, attributeName = "price")
    public Double getPrice() {return price;}

    @DynamoDBAttribute(attributeName = "expiration")
    public Date getExpiration() {return expiration;}

    @DynamoDBAttribute(attributeName = "inStock")
    public Boolean getInStock() {return inStock;}

    @DynamoDBAttribute(attributeName = "quantityAvailable")
    public Integer getQuantityAvailable() {return quantityAvailable;}

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = GROCERY_EXPIRATION_INDEX, attributeName = "type")
    public String getType() {return type;}

    @DynamoDBAttribute(attributeName = "discount")
    public Boolean getDiscount() {return discount;}

    public void setId(String id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public void setDepartment(String department) {this.department = department;}

    public void setPrice(Double price) {this.price = price;}

    public void setExpiration(Date expiration) {this.expiration = expiration;}

    public void setInStock(Boolean inStock) {this.inStock = inStock;}

    public void setQuantityAvailable(Integer quantityAvailable) {this.quantityAvailable = quantityAvailable;}

    public void setType(String type) {this.type = type;}

    public void setDiscount(Boolean discount) {this.discount = discount;}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroceryItemRecord groceryItemRecord = (GroceryItemRecord) o;
        return Objects.equals(id, groceryItemRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
