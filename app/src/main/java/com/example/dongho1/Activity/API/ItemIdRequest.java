package com.example.dongho1.Activity.API;

import com.google.gson.annotations.SerializedName;

public class ItemIdRequest {
    @SerializedName("itemId")
    private String itemId;

    public ItemIdRequest(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
