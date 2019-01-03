package com.future.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.model.list.InventoryUsers;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)

@Document(collection = "inventory")
public class Inventory {

    @Id
    private String id;
    private String detail;
    private Integer stock;
    private Integer available;
    private Integer price;
    private String inventoryId;
    private Date createdAt = new Date();
    private List<InventoryUsers> invenUsers;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;


    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public List<InventoryUsers> getInvenUsers() {
        return invenUsers;
    }

    public void setInvenUsers(List<InventoryUsers> invenUsers) {
        this.invenUsers = invenUsers;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
