package com.example.dongho1.Activity.Model;
import com.google.gson.annotations.SerializedName;

public class Brand {
    @SerializedName("_id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("logo")
    private String logo;
    @SerializedName("countwatch")
    private int countwatch;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getCountwatch() {
        return countwatch;
    }

    public void setCountwatch(int countwatch) {
        this.countwatch = countwatch;
    }
}
