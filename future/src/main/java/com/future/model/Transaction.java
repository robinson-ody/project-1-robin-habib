package com.future.model;

public class Transaction {
    String status;
    String id;
    
    public String getId(){return id};
    public void setId(String id) {this.id=id;}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
