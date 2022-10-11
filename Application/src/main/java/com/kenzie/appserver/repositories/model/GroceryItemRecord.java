package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import java.util.Date;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.util.Objects;

@DynamoDBTable(tableName = "DynamoDBIndexes-Groceries")
public class GroceryItemRecord {

    @DynamoDBAttribute(attributeName = "id")
    private String id;
    @DynamoDBHashKey(attributeName = "name")
    private String name;
    @DynamoDBAttribute(attributeName = "department")
    private String department;
    @DynamoDBAttribute(attributeName = "price")
    private double price;
    @DynamoDBAttribute(attributeName = "expiration")
    private String expiration;
    @DynamoDBAttribute(attributeName = "inStock")
    private boolean inStock;
    @DynamoDBAttribute(attributeName = "quantityAvailable")
    private Integer quantityAvailable;
    @DynamoDBAttribute(attributeName = "type")
    private String type;
    @DynamoDBAttribute(attributeName = "discount")
    private boolean discount;

    public String getName() {return name;}
    public String getId() {return id;}
    public String getDepartment() {return department;}
    public double getPrice() {return price;}
    public String getExpiration() {return expiration;}
    public boolean getInStock() {return inStock;}
    public Integer getQuantityAvailable() {return quantityAvailable;}
    public String getType() {return type;}
    public boolean getDiscount() {return discount;}
    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setPrice(double price) {this.price = price;}
    public void setExpiration(String expiration) {this.expiration = expiration;}
    public void setInStock(boolean inStock) {this.inStock = inStock;}
    public void setQuantityAvailable(Integer quantityAvailable) {this.quantityAvailable = quantityAvailable;}
    public void setType(String type) {this.type = type;}
    public void setDiscount(boolean discount) {this.discount = discount;}
    public void setDepartment(String department) {this.department = department;}

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
