package com.example.dongho1.Activity.Object;

import androidx.cardview.widget.CardView;

import com.example.dongho1.Activity.Model.Item;

import java.util.ArrayList;

public class ObjectWatchTtdh {
    private int id;
    private String _idOder;
    String imgWatch;
    String date;
    int price;
    String txtTrangThai;
    ArrayList<ObjectWatchChitietdonhang> ObjectWatchChitietdonhang;


    public ObjectWatchTtdh(){}

    public ObjectWatchTtdh(int id, String imgWatch, String date, int price, String txtTrangThai, ArrayList<com.example.dongho1.Activity.Object.ObjectWatchChitietdonhang> objectWatchChitietdonhang) {

        this.id = id;
        this.imgWatch = imgWatch;
        this.date = date;
        this.price = price;
        this.txtTrangThai = txtTrangThai;
        ObjectWatchChitietdonhang = objectWatchChitietdonhang;
    }

    public ArrayList<com.example.dongho1.Activity.Object.ObjectWatchChitietdonhang> getObjectWatchChitietdonhang() {
        return ObjectWatchChitietdonhang;
    }

    public void setObjectWatchChitietdonhang(ArrayList<com.example.dongho1.Activity.Object.ObjectWatchChitietdonhang> objectWatchChitietdonhang) {
        ObjectWatchChitietdonhang = objectWatchChitietdonhang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgWatch() {
        return imgWatch;
    }

    public void setImgWatch(String imgWatch) {
        this.imgWatch = imgWatch;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTxtTrangThai() {
        return txtTrangThai;
    }

    public void setTxtTrangThai(String txtTrangThai) {
        this.txtTrangThai = txtTrangThai;
    }

    public String get_idOder() {
        return _idOder;
    }

    public void set_idOder(String _idOder) {
        this._idOder = _idOder;
    }
}
