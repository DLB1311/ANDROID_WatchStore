package com.example.dongho1.Activity.API;

import com.google.gson.annotations.SerializedName;

public class OrderIdRequest {
    @SerializedName("orderId")
    private String orderId;

    public OrderIdRequest(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
