package com.example.dongho1.Activity.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {
    @SerializedName("_id")
    String _id;
    @SerializedName("customer")
    String customer;
    @SerializedName("items")
    List<Item> items;
    @SerializedName("total")
    int total;
    @SerializedName("status")
    int status;
    @SerializedName("date")
    String date;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
