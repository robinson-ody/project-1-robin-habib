package com.future.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import java.util.Date;

@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)

public class Inventory {

    @Id
    private String id;
    private String detail;
    private Integer stock;
    private Integer price;
    private String inventoryId;
    private Date createdAt = new Date();


    public String getId() {
        return id.toString();
    }

    public void setId() {
        this.id = id.toString();
    }

    public String getInventoryId() {
        return inventoryId.toString();
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId.toString();
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
}
