package com.example.dongho1.Activity.Activity1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.dongho1.Activity.API.RetrofitService;
import com.example.dongho1.Activity.API.WatchIdRequest;
import com.example.dongho1.Activity.AdapterCustom.WatchDsspAdapter;
import com.example.dongho1.Activity.AdapterCustom.WatchGiohangAdapter;
import com.example.dongho1.Activity.AdapterCustom.WatchTtdhAdapter;
import com.example.dongho1.Activity.InterfaceAPI.ApiService;
import com.example.dongho1.Activity.Model.Cart;
import com.example.dongho1.Activity.Model.Item;

import com.example.dongho1.Activity.Object.ObjectWatchGiohang;

import com.example.dongho1.R;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GioHang extends AppCompatActivity {
    CardView nutTroVe, nutCheckout;
    Context context;
    ListView listviewWatch_Giohang;
    public static ArrayList<ObjectWatchGiohang> arrWatchGiohang;
    ObjectWatchGiohang objectWatchGiohang;
    WatchGiohangAdapter watchGiohangAdapter;

    public static TextView sumPrice,taxPrice,totalPrice;
    public static Boolean themWatchvaogio=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        AnhXa();
        AddItem();
        context=this;
        // Header Start---------------------------------------------------------------
        nutTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        nutCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
                Call<ResponseBody> call = apiService.createOrder("Bearer "+TrangChu.tokenUser);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("TAG", response.message()+"");
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(v.getContext(), TrangThaiDonHang.class);
                            v.getContext().startActivity(intent);
                        } else {
                            Toast.makeText(GioHang.this, "LỖI", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(GioHang.this, "LỖI", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
        // Header End---------------------------------------------------------------

        // Watch Start---------------------------------------------------------------
        watchGiohangAdapter = new WatchGiohangAdapter(context,arrWatchGiohang);
        listviewWatch_Giohang.setAdapter(watchGiohangAdapter);
        listviewWatch_Giohang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(GioHang.this, "Item: "+GioHang.arrWatchGiohang.get(position).getWatchName(), Toast.LENGTH_SHORT).show();
                TrangChu.getWatchName = arrWatchGiohang.get(position).getWatchName();
                TrangChu.getTypeName = arrWatchGiohang.get(position).getType();
                TrangChu.getDescription = arrWatchGiohang.get(position).getDescription();
                TrangChu.getUrlImgWatch = TrangChu.linkhttp+ arrWatchGiohang.get(position).getImgWatch();
                TrangChu.getPrice=arrWatchGiohang.get(position).getPrice()+"";
                TrangChu.getQuantity=arrWatchGiohang.get(position).getQuantity();
                TrangChu.getIdCurWatch = arrWatchGiohang.get(position).get_id();
                TrangChu.getLineName = arrWatchGiohang.get(position).getWatchLine();
                TrangChu.getBrandName = arrWatchGiohang.get(position).getWatchBrand();

                Intent intent = new Intent(v.getContext(), ChiTiet.class);
                v.getContext().startActivity(intent);
            }
        });
        // Watch End---------------------------------------------------------------

        // Price Start---------------------------------------------------------------
        updatePrice();
        // Price End---------------------------------------------------------------


    }
    public void AnhXa() {
        nutTroVe = (CardView) findViewById(R.id.btnTurnBack_giohang);
        nutCheckout = (CardView) findViewById(R.id.btnCheckout_giohang);
        arrWatchGiohang = new ArrayList<>();
        listviewWatch_Giohang = (ListView) findViewById(R.id.listviewGiohang);
        sumPrice = (TextView) findViewById(R.id.sumPrice_Giohang);
        taxPrice = (TextView) findViewById(R.id.taxPrice_Giohang);
        totalPrice = (TextView) findViewById(R.id.totalPrice_Giohang);
    }

    public void AddItem() {
//        if (themWatchvaogio==true) {
//            ThemWatchvaogio("Bearer "+TrangChu.tokenUser);
//            GioHang.themWatchvaogio=false;
//            xuatDSWatch("Bearer "+TrangChu.tokenUser);
//        }

        if (themWatchvaogio==true) {
            ThemWatchvaogio("Bearer "+TrangChu.tokenUser, new Callback<Cart>() {
                @Override
                public void onResponse(Call<Cart> call, Response<Cart> response) {
                    if (response.isSuccessful()) {
                        xuatDSWatch("Bearer "+TrangChu.tokenUser);
                    } else {
                        Log.e("API them vao gio", "Error: " + response.code() + " - " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<Cart> call, Throwable t) {
                    Log.e("API them gio hang", "Error: " + t.getMessage());
                }
            });
        }
        else {
            xuatDSWatch("Bearer "+TrangChu.tokenUser);
        }
        themWatchvaogio=false;
    }

    public void ThemWatchvaogio(String authHeader, Callback<Cart> callback)  {
        ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
        WatchIdRequest watchIdRequest=new WatchIdRequest(TrangChu.getIdCurWatch);
        Log.d("TAG", TrangChu.getIdCurWatch);
        Call<Cart> call = apiService.addWatchToCart(authHeader,watchIdRequest);
        call.enqueue(callback);
    }

//    public void ThemWatchvaogio(String authHeader)  {
//        ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
//        WatchIdRequest watchIdRequest=new WatchIdRequest(TrangChu.getIdCurWatch);
//        Call<Cart> call = apiService.addWatchToCart(authHeader,watchIdRequest);
//        call.enqueue(new Callback<Cart>() {
//            @Override
//            public void onResponse(Call<Cart> call, Response<Cart> response) {
//                if (response.isSuccessful()) {
//
//
//                }
//                else {
//                    Log.e("API them vao gio", "Error: " + response.code() + " - " + response.message());
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<Cart> call, Throwable t) {
//                Log.e("API them gio hang", "Error: " + t.getMessage());
//            }
//        });
//
//    }

    public void xuatDSWatch(String authHeader) {
        arrWatchGiohang.clear();

        ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
        Call<Cart> call = apiService.getCart(authHeader);

        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()) {
                    int m = 0;
                    Cart carts = response.body();

                    for(Item item : carts.getItems())
                    {
                        objectWatchGiohang = new ObjectWatchGiohang();
//                        //objectWatchGiohang.setId(m);
                        objectWatchGiohang.set_id(item.get_id());

                        objectWatchGiohang.setIdWatch(item.getWatch().get_id());
                        objectWatchGiohang.setImgWatch(item.getWatch().getImage());
                        objectWatchGiohang.setPrice(item.getWatch().getPrice());
                        objectWatchGiohang.setSoluong(item.getQuantity());
//                        objectWatchGiohang.setSoluong(1);
                        objectWatchGiohang.setWatchName(item.getWatch().getName());
                        objectWatchGiohang.setQuantity(item.getWatch().getQuantity());

                        objectWatchGiohang.setWatchLine(item.getWatch().getLine().getName());
                        objectWatchGiohang.setWatchBrand(item.getWatch().getLine().getBrand().getName());
                        objectWatchGiohang.setType(item.getWatch().getType());
                        objectWatchGiohang.setDescription(item.getWatch().getDescription());

                        arrWatchGiohang.add(objectWatchGiohang);
                        m++;
                    }
                    watchGiohangAdapter = new WatchGiohangAdapter(GioHang.this,arrWatchGiohang);
                    listviewWatch_Giohang.setAdapter(watchGiohangAdapter);
                    updatePrice();
                    watchGiohangAdapter.notifyDataSetChanged();

                }
                else {
                    Log.e("API", "Error: " + response.code() + " - " + response.message());
                }
            }


            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Log.e("API xuat danh sach", "Error: " + t.getMessage());
            }
        });
    }

    public static void updatePrice(){
        int sum=0;
        for (int i=0; i<arrWatchGiohang.size(); i++)
            sum=sum+(arrWatchGiohang.get(i).getPrice()*arrWatchGiohang.get(i).getSoluong());
        sumPrice.setText("$"+sum);
        taxPrice.setText("$"+((float)(sum*10/100)));
        totalPrice.setText("$"+(sum+((float)(sum*10/100))));
    }
}