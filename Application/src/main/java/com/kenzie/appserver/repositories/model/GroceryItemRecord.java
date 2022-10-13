package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.util.Objects;

@DynamoDBTable(tableName = "DynamoDBIndexes-Groceries")
public class GroceryItemRecord {

    private String id;
    private String name;
    private String department;
    private double price;
    private String expiration;
    private boolean inStock;
    private Integer quantityAvailable;
    private String type;
    private boolean discount;

    @DynamoDBHashKey(attributeName = "name")
    public String getName() {return name;}

    @DynamoDBAttribute(attributeName = "id")
    public String getId() {return id;}

    @DynamoDBAttribute(attributeName = "department")
    public String getDepartment() {return department;}

    @DynamoDBAttribute(attributeName = "price")
    public double getPrice() {return price;}

    @DynamoDBAttribute(attributeName = "expiration")
    public String getExpiration() {return expiration;}

    @DynamoDBAttribute(attributeName = "inStock")
    public boolean getInStock() {return inStock;}

    @DynamoDBAttribute(attributeName = "quantityAvailable")
    public Integer getQuantityAvailable() {return quantityAvailable;}

    @DynamoDBAttribute(attributeName = "type")
    public String getType() {return type;}

    @DynamoDBAttribute(attributeName = "discount")
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
