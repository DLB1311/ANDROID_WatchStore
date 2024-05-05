package com.example.dongho1.Activity.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class addWatchToCart {
    @SerializedName("_id")
    private String _id;
    @SerializedName("watch")
    private Watch watch;
    @SerializedName("quantity")
    private int quantity;


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
