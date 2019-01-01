package com.future.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)

public class Transaction {
    @Id
    private String inventoryId;
    private Date createdAt = new Date();
    private List <transData> transcData;

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public List<transData> getTranscData() {
        return transcData;
    }

    public void setTranscData(List<transData> transcData) {
        this.transcData = transcData;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
