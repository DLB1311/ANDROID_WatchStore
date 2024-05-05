package com.example.dongho1.Activity.Object;

import androidx.cardview.widget.CardView;

public class ObjectWatchGiohang {
    private int id;
    private String _id;
    String idWatch;
    String watchName;
    String watchBrand;
    String type;
    String watchLine;
    String description;
    int price;
    String imgWatch;
    int quantity;
    int soluong;
    CardView cardview = null;
    CardView cardviewCong = null;
    CardView cardviewTru = null;
    CardView cardviewClose = null;



    public ObjectWatchGiohang(){}


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

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public CardView getCardview() {
        return cardview;
    }

    public void setCardview(CardView cardview) {
        this.cardview = cardview;
    }

    public CardView getCardviewCong() {
        return cardviewCong;
    }

    public void setCardviewCong(CardView cardviewCong) {
        this.cardviewCong = cardviewCong;
    }

    public CardView getCardviewTru() {
        return cardviewTru;
    }

    public void setCardviewTru(CardView cardviewTru) {
        this.cardviewTru = cardviewTru;
    }

    public CardView getCardviewClose() {
        return cardviewClose;
    }

    public void setCardviewClose(CardView cardviewClose) {
        this.cardviewClose = cardviewClose;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIdWatch() {
        return idWatch;
    }

    public void setIdWatch(String idWatch) {
        this.idWatch = idWatch;
    }

    public String getWatchBrand() {
        return watchBrand;
    }

    public void setWatchBrand(String watchBrand) {
        this.watchBrand = watchBrand;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
