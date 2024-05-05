package com.example.dongho1.Activity.AdapterCustom;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.dongho1.Activity.API.ItemIdRequest;
import com.example.dongho1.Activity.API.RetrofitService;
import com.example.dongho1.Activity.API.WatchIdRequest;
import com.example.dongho1.Activity.Activity1.TrangChu;
import com.example.dongho1.Activity.InterfaceAPI.ApiService;
import com.example.dongho1.Activity.Model.Cart;
import com.example.dongho1.Activity.Object.ObjectWatchChitietdonhang;
import com.example.dongho1.Activity.Object.ObjectWatchGiohang;
import com.example.dongho1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WatchChitietdonhangAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private LayoutInflater layoutInflater;
    ArrayList<ObjectWatchChitietdonhang> listChitietdonhang;
    private ObjectWatchChitietdonhang objectWatchChitietdonhang;
    public static ArrayList<Integer> dsLTCduocchon = new ArrayList<Integer>();


    public WatchChitietdonhangAdapter(Context context, int layout, ArrayList<ObjectWatchChitietdonhang> listChitietdonhang) {
        this.context = context;
        this.layout=layout;
        this.listChitietdonhang=listChitietdonhang;
    }


    @Override
    public int getCount() {
        return listChitietdonhang.size();
    }

    @Override
    public Object getItem(int i) {
        return listChitietdonhang.get(i);
    }


    public long getItemId(int i) {
        return listChitietdonhang.get(i).getId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_listview_chitietdonhang, null);
        }

        ImageView imageView = view.findViewById(R.id.imgWatch_ctdonhang);
        TextView name = view.findViewById(R.id.Name_ctdonhang);
        TextView price = view.findViewById(R.id.Price_ctdonhang);
        TextView soluong = view.findViewById(R.id.soluong_ctdonhang);
        CardView cardview = view.findViewById(R.id.Cardview_ctdonhang);

        objectWatchChitietdonhang = listChitietdonhang.get(position);

//        Picasso.get().load(TrangChu.getUrlImgWatch).into(imageView);
//        name.setText(objectWatchGiohang.getWatchName());
//        objectWatchGiohang.setQuantity(TrangChu.getQuantity);
//        if(objectWatchGiohang.getQuantity()==0) {
//            price.setText("SOLD OUT");
//            price.setTextColor(Color.parseColor("#F08080"));
//            price.setTypeface(null, Typeface.BOLD);
//        }
//        else
//            price.setText("$"+objectWatchGiohang.getPrice());
//
//        soluong.setText(String.valueOf(objectWatchGiohang.getSoluong()));


//        objectWatchGiohang.setId(objectWatchGiohang.getId());
        objectWatchChitietdonhang.set_id(objectWatchChitietdonhang.get_id());
        objectWatchChitietdonhang.setIdWatch(objectWatchChitietdonhang.getIdWatch());
        Picasso.get().load(TrangChu.linkhttp+objectWatchChitietdonhang.getImgWatch()).into(imageView);
        price.setText("$"+objectWatchChitietdonhang.getPrice()+"");
        soluong.setText(objectWatchChitietdonhang.getSoluong()+"");
        name.setText(objectWatchChitietdonhang.getWatchName());

        objectWatchChitietdonhang.setWatchLine(objectWatchChitietdonhang.getWatchLine());
        objectWatchChitietdonhang.setWatchBrand(objectWatchChitietdonhang.getWatchBrand());
        objectWatchChitietdonhang.setType(objectWatchChitietdonhang.getType());
        objectWatchChitietdonhang.setDescription(objectWatchChitietdonhang.getDescription());


        return view;
    }
}
