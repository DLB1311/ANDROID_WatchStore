package com.example.dongho1.Activity.Model;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("_id")
    String _id;
    @SerializedName("watch")
    Watch watch;
    @SerializedName("quantity")
    int quantity;

    @SerializedName("price")
    int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Watch getWatch() {
        return watch;
    }

    public void setWatch(Watch watch) {
        this.watch = watch;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
