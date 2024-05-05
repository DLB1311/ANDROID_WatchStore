package com.example.dongho1.Activity.API;

import com.google.gson.annotations.SerializedName;

public class WatchIdRequest {
    @SerializedName("watchId")
    private String watchId;

    public WatchIdRequest(String watchId) {
        this.watchId = watchId;
    }

    public String getWatchId() {
        return watchId;
    }

    public void setWatchId(String watchId) {
        this.watchId = watchId;
    }

}
