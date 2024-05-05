package com.example.dongho1.Activity.AdapterCustom;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import com.example.dongho1.Activity.API.ItemIdRequest;
import com.example.dongho1.Activity.API.RetrofitService;
import com.example.dongho1.Activity.API.WatchIdRequest;
import com.example.dongho1.Activity.Activity1.GioHang;
import com.example.dongho1.Activity.Activity1.TrangChu;
import com.example.dongho1.Activity.Activity1.TrangThaiDonHang;
import com.example.dongho1.Activity.InterfaceAPI.ApiService;
import com.example.dongho1.Activity.Model.Cart;
import com.example.dongho1.Activity.Object.ObjectWatchDssp;
import com.example.dongho1.Activity.Object.ObjectWatchGiohang;
import com.example.dongho1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WatchGiohangAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private LayoutInflater layoutInflater;
    ArrayList<ObjectWatchGiohang> listGiohang;
    private ObjectWatchGiohang objectWatchGiohang;


    public WatchGiohangAdapter(Context context, ArrayList<ObjectWatchGiohang> listGiohang) {
        this.context = context;
        this.listGiohang= listGiohang;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
//    public WatchGiohangAdapter(Context context, int layout, ArrayList<ObjectWatchGiohang> listGiohang) {
//        this.context = context;
//        this.layout=layout;
//        this.listGiohang=listGiohang;
//    }


    @Override
    public int getCount() {
        return listGiohang.size();
    }

    @Override
    public Object getItem(int i) {
        return listGiohang.get(i);
    }


    public long getItemId(int i) {
        return listGiohang.get(i).getId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_listview_giohang, null);
        }

        ImageView imageView = view.findViewById(R.id.imgWatch_giohang);
        TextView name = view.findViewById(R.id.Name_giohang);
        TextView price = view.findViewById(R.id.Price_giohang);
        TextView soluong = view.findViewById(R.id.soluong_giohang);
        CardView cardviewCong = view.findViewById(R.id.btnCong_giohang);
        CardView cardviewTru = view.findViewById(R.id.btnTru_giohang);
        CardView cardviewClose = view.findViewById(R.id.btnClose_giohang);
        CardView cardview = view.findViewById(R.id.Cardview_giohang);

        objectWatchGiohang = listGiohang.get(position);



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
        objectWatchGiohang.set_id(objectWatchGiohang.get_id());
        objectWatchGiohang.setIdWatch(objectWatchGiohang.getIdWatch());
        Picasso.get().load(TrangChu.linkhttp+objectWatchGiohang.getImgWatch()).into(imageView);
        price.setText("$"+objectWatchGiohang.getPrice()+"");
        soluong.setText(objectWatchGiohang.getSoluong()+"");
        name.setText(objectWatchGiohang.getWatchName());

        objectWatchGiohang.setWatchLine(objectWatchGiohang.getWatchLine());
        objectWatchGiohang.setWatchBrand(objectWatchGiohang.getWatchBrand());
        objectWatchGiohang.setType(objectWatchGiohang.getType());
        objectWatchGiohang.setDescription(objectWatchGiohang.getDescription());
        Log.d("posision", objectWatchGiohang.getIdWatch()+"");
        cardviewCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("posision", position+"");
                ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
                WatchIdRequest watchIdRequest=new WatchIdRequest(objectWatchGiohang.getIdWatch());
                Call<Cart> call = apiService.addWatchToCart("Bearer "+TrangChu.tokenUser,watchIdRequest);
                call.enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        if (response.isSuccessful()) {
                            if (listGiohang.get(position).getSoluong()<listGiohang.get(position).getQuantity()) {
                                int curSL = listGiohang.get(position).getSoluong();
                                curSL++;
                                listGiohang.get(position).setSoluong(curSL);
                                notifyDataSetChanged();
                                GioHang.updatePrice();
                                Log.d("nutcong", "thanhcong");
                            }
                        }
                        else Log.d("nutCONGloi", "FAIL");
                    }

                    @Override
                    public void onFailure(Call<Cart> call, Throwable t) {

                    }
                });
            }
        });
        cardviewTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("posision", position+"");
                ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
                WatchIdRequest watchIdRequest=new WatchIdRequest(listGiohang.get(position).getIdWatch());
                Call<Cart> call = apiService.removeWatchFromCart("Bearer "+TrangChu.tokenUser,watchIdRequest);
                call.enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        if (response.isSuccessful()) {
                            int curSL = listGiohang.get(position).getSoluong();
                            if (curSL!=1) {
                                curSL--;
                                listGiohang.get(position).setSoluong(curSL);
                                notifyDataSetChanged();
                                GioHang.updatePrice();
                            }
                            else {
                                GioHang.arrWatchGiohang.remove(position);
                                notifyDataSetChanged();
                                GioHang.updatePrice();
                            }
                        }
                        else Log.d("nutTRUloi", "FAIL");
                    }

                    @Override
                    public void onFailure(Call<Cart> call, Throwable t) {

                    }
                });
            }
        });
        cardviewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
                ItemIdRequest itemIdRequest=new ItemIdRequest(listGiohang.get(position).get_id());
                Call<Cart> call = apiService.removeItemFromCart("Bearer "+TrangChu.tokenUser, itemIdRequest);
                call.enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        if (response.isSuccessful()) {
                            GioHang.arrWatchGiohang.remove(position);
                            notifyDataSetChanged();
                            GioHang.updatePrice();
                        }
                        else Log.d("nutCLOSEloi", "FAIL");
                    }

                    @Override
                    public void onFailure(Call<Cart> call, Throwable t) {

                    }
                });
            }
        });

        return view;
    }
}
