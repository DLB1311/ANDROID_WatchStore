package com.example.dongho1.Activity.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart {
    @SerializedName("_id")
    String _id;
    @SerializedName("items")
    List<Item> items;
    @SerializedName("customer")
    String customer;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
