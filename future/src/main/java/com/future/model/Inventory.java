package com.future.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.Date;

@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)

public class Inventory {

    @Id
    private String id;
    private String detail;
    private Integer stock;
    private Integer price;
    @NotBlank
    @Size(max=100)
    @Indexed(unique=true)
    private String inventoryId;

    private Date createdAt = new Date();

    public Inventory() {
        super();
    }

    public Inventory(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public String toString() {
        return String.format(
                "Inventory[id=%s, inventoryId='%s',detail='%s']",
                id, inventoryId, detail);
    }






/*
 @Id
    public String id;
    public String inventoryId;
    public String detail;
    public Integer stock;
    public Integer price;
    public Inventory() {}
    public Inventory(String id, String inventoryId, String detail, Integer stock,Integer price) {
        this.id = id;
        this.inventoryId = inventoryId;
        this.detail = detail;
        this.stock=stock;
        this.price=price;
    }
    public void setid(String id) { this.id = id; }
    public String getid() { return id; }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
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

    @Override
    public String toString() {
        return String.format(
                "Todo[id=%s, title='%s', completed='%s']",
                id, title, completed);
    }*/

}
