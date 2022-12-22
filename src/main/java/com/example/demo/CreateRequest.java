package com.example.demo;

public class CreateRequest {
    private boolean status;

    public CreateRequest(boolean status) {
        this.status = status;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
