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

import com.example.dongho1.Activity.API.OrderResponse;
import com.example.dongho1.Activity.API.RetrofitService;
import com.example.dongho1.Activity.AdapterCustom.WatchGiohangAdapter;
import com.example.dongho1.Activity.AdapterCustom.WatchTtdhAdapter;
import com.example.dongho1.Activity.InterfaceAPI.ApiService;
import com.example.dongho1.Activity.Model.Cart;
import com.example.dongho1.Activity.Model.Item;
import com.example.dongho1.Activity.Model.Order;
import com.example.dongho1.Activity.Object.ObjectWatchChitietdonhang;
import com.example.dongho1.Activity.Object.ObjectWatchGiohang;
import com.example.dongho1.Activity.Object.ObjectWatchTtdh;
import com.example.dongho1.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrangThaiDonHang extends AppCompatActivity {
    CardView nutTroVe,nutTrangChu,nutCart;
    TextView ten;
    ImageButton nutSetting;
    Context context;
    ListView listviewWatch_TTDH;
    public static ArrayList<ObjectWatchTtdh> arrWatchTtdh;
    ObjectWatchTtdh objectWatchTtdh;
    ObjectWatchChitietdonhang objectWatchDonHang;
    public static ArrayList<ObjectWatchChitietdonhang> arrWatchDonHang;
    public static ArrayList<ObjectWatchChitietdonhang> arrWatchChitietdonhang;
    WatchTtdhAdapter watchTtdhAdapter;

    public static String idOrder;
    public static boolean allowCancel=false;


    //public static int totaldetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_thai_don_hang);
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
        nutTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TrangChu.class);
                v.getContext().startActivity(intent);
            }
        });
        nutSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SuaThongTin.class);
                v.getContext().startActivity(intent);
            }
        });
        nutCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("token",TrangChu.tokenUser);

                ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);

                Call<Cart> call = apiService.getCart("Bearer "+TrangChu.tokenUser);
                call.enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(v.getContext(), GioHang.class);
                            v.getContext().startActivity(intent);
                        } else {
                            Intent intent = new Intent(v.getContext(), DangNhap.class);
                            v.getContext().startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Cart> call, Throwable t) {

                    }
                });
            }
        });
        // Header End---------------------------------------------------------------

        // Watch Start---------------------------------------------------------------
        watchTtdhAdapter = new WatchTtdhAdapter(context,R.layout.item_listview_trangthaidonhang,arrWatchTtdh);
        listviewWatch_TTDH.setAdapter(watchTtdhAdapter);
        listviewWatch_TTDH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                idOrder = arrWatchTtdh.get(position).get_idOder();
                arrWatchChitietdonhang=new ArrayList<>();
                for (ObjectWatchChitietdonhang i : arrWatchTtdh.get(position).getObjectWatchChitietdonhang())
                    arrWatchChitietdonhang.add(i);

                if(arrWatchTtdh.get(position).getTxtTrangThai()=="Progressing")
                    allowCancel=true;
                else
                    allowCancel=false;

                Intent intent = new Intent(v.getContext(), ChiTietDonHang.class);
                v.getContext().startActivity(intent);
            }
        });
        // Watch End---------------------------------------------------------------

    }
    public void AnhXa() {
        nutTroVe = (CardView) findViewById(R.id.btnTurnBackTrangThai);
        nutTrangChu = (CardView) findViewById(R.id.btnTrangChu);
        nutSetting = (ImageButton) findViewById(R.id.btnSettingTrangThai);
        nutCart = (CardView) findViewById(R.id.btnCart);
        arrWatchTtdh = new ArrayList<>();
        listviewWatch_TTDH = (ListView) findViewById(R.id.listviewTrangThaiDonHang);
        ten = (TextView) findViewById(R.id.Ten_TrangThai);
    }

    public void AddItem() {
        ten.setText(DangNhap.name);
        xuatDSWatch();
    }
    public void xuatDSWatch() {
        ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
        Log.d("tokenttdh", "Bearer "+TrangChu.tokenUser);
        Call<OrderResponse> call = apiService.getOrder("Bearer "+TrangChu.tokenUser);
        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()) {
                    int i = 0;
                    OrderResponse orders = response.body();

                    for(Order order : orders.getOrders())
                    {
                        arrWatchDonHang = new ArrayList<>();
                        objectWatchTtdh = new ObjectWatchTtdh();
                        objectWatchTtdh.setId(i);
                        objectWatchTtdh.set_idOder(order.get_id());
                        objectWatchTtdh.setImgWatch(order.getItems().get(0).getWatch().getImage());
                        objectWatchTtdh.setDate(order.getDate().substring(0,10) + order.getDate().substring(11,19));
                        int k=0;

                        for(Item item : order.getItems()) {

                            objectWatchDonHang = new ObjectWatchChitietdonhang();
                            objectWatchDonHang.setId(k);
                            objectWatchDonHang.set_id(item.get_id());
                            objectWatchDonHang.setIdWatch(item.getWatch().get_id());
                            objectWatchDonHang.setWatchName(item.getWatch().getName());
                            objectWatchDonHang.setWatchBrand(item.getWatch().getLine().getBrand().getName());
                            objectWatchDonHang.setType(item.getWatch().getType());
                            objectWatchDonHang.setWatchLine(item.getWatch().getLine().getName());
                            objectWatchDonHang.setDescription(item.getWatch().getDescription());
                            objectWatchDonHang.setPrice(item.getPrice());
                            objectWatchDonHang.setImgWatch(item.getWatch().getImage());
                            objectWatchDonHang.setQuantity(item.getQuantity());
                            objectWatchDonHang.setSoluong(item.getWatch().getQuantity());
                            arrWatchDonHang.add(objectWatchDonHang);

                            k++;
                        }
                        objectWatchTtdh.setObjectWatchChitietdonhang(arrWatchDonHang);

                        Log.d("test1",objectWatchTtdh.getObjectWatchChitietdonhang().toString());
                        Log.d("===============","====================================");

                        objectWatchTtdh.setPrice(order.getTotal());
                        if(order.getStatus()==0)
                            objectWatchTtdh.setTxtTrangThai("Progressing");
                        if(order.getStatus()==1)
                            objectWatchTtdh.setTxtTrangThai("Purchasing");
                        if(order.getStatus()==2)
                            objectWatchTtdh.setTxtTrangThai("Completed");
                        if(order.getStatus()==3)
                            objectWatchTtdh.setTxtTrangThai("Aborting");
                        if(order.getStatus()==4)
                            objectWatchTtdh.setTxtTrangThai("Aborted");
                        i++;
                        arrWatchTtdh.add(objectWatchTtdh);

                        Log.d("=====================", arrWatchTtdh.get(i-1).getObjectWatchChitietdonhang() + "");

                    }
                    watchTtdhAdapter = new WatchTtdhAdapter(TrangThaiDonHang.this,R.layout.item_listview_trangthaidonhang,arrWatchTtdh);
                    listviewWatch_TTDH.setAdapter(watchTtdhAdapter);
                    watchTtdhAdapter.notifyDataSetChanged();

                }
                else {
                    Log.e("API", "Error: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Log.e("API xuat ds order", "Error: " + t.getMessage());
            }
        });
    }
}