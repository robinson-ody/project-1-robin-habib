package com.future.model;

import org.springframework.data.annotation.Id;

public class transData {

    @Id
    private String inventoryId;
    private String email;
    private Integer qty;
    private String status;

    public String getInventoryId() {
        return inventoryId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
