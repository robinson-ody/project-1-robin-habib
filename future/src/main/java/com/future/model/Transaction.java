package com.future.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)

public class Transaction {
    @Id
    private String id;
    private Date createdAt = new Date();
    private List <transData> transcData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
