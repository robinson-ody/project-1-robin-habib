package com.future.model;

public class LoginResponse {

    private String status;
    private boolean success;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status= status;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
