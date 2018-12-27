package com.future.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.future.services.NextSequenceService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
//import com.future.services.NextSequenceService;

import javax.validation.constraints.Size;
import java.util.Date;

@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)

public class Inventory {

/*
    @Autowired
    NextSequenceService nextSequenceService;
*/
/*

    @Transient
    public static final String SEQUENCE_NAME = "inventory_sequence";
*/

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


    public String getId() {
        return id;
    }

    public void setId() {
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
}
