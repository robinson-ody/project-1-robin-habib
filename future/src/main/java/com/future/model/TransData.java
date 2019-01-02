package com.future.model;

import org.springframework.data.annotation.Id;

public class TransData {

    @Id
    private String inventoryId;
    private Integer qty;

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

}
