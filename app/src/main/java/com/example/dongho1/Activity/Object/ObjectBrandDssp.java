package com.example.dongho1.Activity.Object;

import androidx.cardview.widget.CardView;

public class ObjectBrandDssp {
    private int id;
    private String _id;
    String brandName;
    int slconlai;
    String imgBrand;
    CardView cardBrand = null;
    CardView cardslConLai = null;

    public ObjectBrandDssp(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSlconlai() {
        return slconlai;
    }

    public void setSlconlai(int slconlai) {
        this.slconlai = slconlai;
    }

    public String getImgBrand() {
        return imgBrand;
    }

    public void setImgBrand(String imgBrand) {
        this.imgBrand = imgBrand;
    }

    public CardView getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(CardView cardBrand) {
        this.cardBrand = cardBrand;
    }

    public CardView getCardslConLai() {
        return cardslConLai;
    }

    public void setCardslConLai(CardView cardslConLai) {
        this.cardslConLai = cardslConLai;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
