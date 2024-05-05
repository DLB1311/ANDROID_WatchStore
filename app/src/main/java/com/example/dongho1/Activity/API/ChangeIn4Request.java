package com.example.dongho1.Activity.API;

import com.google.gson.annotations.SerializedName;

public class ChangeIn4Request {
    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;

    public ChangeIn4Request(String name, String address) {

        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
