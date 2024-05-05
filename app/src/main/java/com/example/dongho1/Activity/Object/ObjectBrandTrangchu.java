package com.example.dongho1.Activity.Object;

import androidx.cardview.widget.CardView;

public class ObjectBrandTrangchu {
    private String id;
    String brandName;
    String imgBrand;
    int imgBar;
    CardView cardBrand = null;


    public ObjectBrandTrangchu(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getImgBrand() {
        return imgBrand;
    }

    public void setImgBrand(String imgBrand) {
        this.imgBrand = imgBrand;
    }

    public int getImgBar() {
        return imgBar;
    }

    public void setImgBar(int imgBar) {
        this.imgBar = imgBar;
    }

    public CardView getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(CardView cardBrand) {
        this.cardBrand = cardBrand;
    }
}

