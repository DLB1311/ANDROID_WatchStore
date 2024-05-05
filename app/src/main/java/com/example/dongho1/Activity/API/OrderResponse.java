package com.example.dongho1.Activity.API;

import com.example.dongho1.Activity.Model.Customer;
import com.example.dongho1.Activity.Model.Order;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderResponse {

    @SerializedName("orders")
    private ArrayList<Order> orders;

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
