package com.example.dongho1.Activity.Object;

import androidx.cardview.widget.CardView;

public class ObjectWatchDssp {
    private int id;
    String _id;
    String watchName;
    String watchBrand;
    String type;
    String watchLine;
    String description;
    int price;
    String imgWatch;
    int quantity;

    public ObjectWatchDssp(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getWatchName() {
        return watchName;
    }

    public void setWatchName(String watchName) {
        this.watchName = watchName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgWatch() {
        return imgWatch;
    }

    public void setImgWatch(String imgWatch) {
        this.imgWatch = imgWatch;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getWatchBrand() {
        return watchBrand;
    }

    public void setWatchBrand(String watchBrand) {
        this.watchBrand = watchBrand;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWatchLine() {
        return watchLine;
    }

    public void setWatchLine(String watchLine) {
        this.watchLine = watchLine;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
