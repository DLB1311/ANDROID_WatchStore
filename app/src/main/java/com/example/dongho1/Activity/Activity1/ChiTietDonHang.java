package com.example.dongho1.Activity.Activity1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.dongho1.Activity.API.ItemIdRequest;
import com.example.dongho1.Activity.API.OrderIdRequest;
import com.example.dongho1.Activity.API.OrderResponse;
import com.example.dongho1.Activity.API.RetrofitService;
import com.example.dongho1.Activity.API.SignInRequest;
import com.example.dongho1.Activity.API.SignInResponse;
import com.example.dongho1.Activity.API.WatchIdRequest;
import com.example.dongho1.Activity.AdapterCustom.WatchChitietdonhangAdapter;
import com.example.dongho1.Activity.InterfaceAPI.ApiService;
import com.example.dongho1.Activity.Model.Cart;
import com.example.dongho1.Activity.Model.Item;
import com.example.dongho1.Activity.Object.ObjectWatchChitietdonhang;
import com.example.dongho1.R;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietDonHang extends AppCompatActivity {
    CardView nutTroVe,nutCancel;
    Context context;
    ListView listviewWatch_Chitietdonhang;
    public static ArrayList<ObjectWatchChitietdonhang> arrWatchChitietdonhang;
    ObjectWatchChitietdonhang objectWatchChitietdonhang;
    WatchChitietdonhangAdapter watchChitietdonhangAdapter;

    public static TextView sumPrice,taxPrice,totalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);
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

        // Header End---------------------------------------------------------------

        // Watch Start---------------------------------------------------------------
        watchChitietdonhangAdapter = new WatchChitietdonhangAdapter(context,R.layout.item_listview_chitietdonhang,arrWatchChitietdonhang);
        listviewWatch_Chitietdonhang.setAdapter(watchChitietdonhangAdapter);
        listviewWatch_Chitietdonhang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TrangChu.getWatchName = arrWatchChitietdonhang.get(position).getWatchName();
                TrangChu.getTypeName = arrWatchChitietdonhang.get(position).getType();
                TrangChu.getDescription = arrWatchChitietdonhang.get(position).getDescription();
                TrangChu.getUrlImgWatch = TrangChu.linkhttp+ arrWatchChitietdonhang.get(position).getImgWatch();
                TrangChu.getPrice=arrWatchChitietdonhang.get(position).getPrice()+"";
                TrangChu.getQuantity=arrWatchChitietdonhang.get(position).getQuantity();
                TrangChu.getIdCurWatch = arrWatchChitietdonhang.get(position).get_id();
                TrangChu.getLineName = arrWatchChitietdonhang.get(position).getWatchLine();
                TrangChu.getBrandName = arrWatchChitietdonhang.get(position).getWatchBrand();
                Intent intent = new Intent(v.getContext(), ChiTiet.class);
                v.getContext().startActivity(intent);
            }
        });
        // Watch End---------------------------------------------------------------

        // Price Start---------------------------------------------------------------
        updatePrice();
        // Price End---------------------------------------------------------------

        if(TrangThaiDonHang.allowCancel==false) {
            nutCancel.setEnabled(false);
            nutCancel.setOnClickListener(null);
            nutCancel.setCardBackgroundColor(Color.parseColor("#8F8F8F"));
        }
        else {
            nutCancel.setEnabled(true);
            nutCancel.setCardBackgroundColor(Color.parseColor("#C09E57"));
            nutCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
                    OrderIdRequest orderIdRequest = new OrderIdRequest(TrangThaiDonHang.idOrder);
                    //Toast.makeText(context, TrangThaiDonHang.idOrder, Toast.LENGTH_SHORT).show();
                    Call<ResponseBody> call = apiService.cancelOrder("Bearer " + TrangChu.tokenUser, TrangThaiDonHang.idOrder);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(v.getContext(), TrangThaiDonHang.class);
                                v.getContext().startActivity(intent);
                            } else Log.d("nutCancel:", "FAIL");
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
            });
        }

    }
    public void AnhXa() {
        nutTroVe = (CardView) findViewById(R.id.btnTurnBack_ctdonhang);
        arrWatchChitietdonhang = new ArrayList<>();
        listviewWatch_Chitietdonhang = (ListView) findViewById(R.id.listview_ctdonhang);
        sumPrice = (TextView) findViewById(R.id.sumPrice_ctdonhang);
        taxPrice = (TextView) findViewById(R.id.taxPrice_ctdonhang);
        totalPrice = (TextView) findViewById(R.id.totalPrice_ctdonhang);
        nutCancel = (CardView) findViewById(R.id.btnCancel_chitietdonhang);
    }

    public void AddItem() {
            xuatDSWatch();
//            totalPrice.setText(TrangThaiDonHang.totaldetail+"");
    }

    public void ThemWatchvaoChitietdonhang(String authHeader, Callback<Cart> callback)  {
        ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
        WatchIdRequest watchIdRequest=new WatchIdRequest(TrangChu.getIdCurWatch);
        Call<Cart> call = apiService.addWatchToCart(authHeader,watchIdRequest);
        call.enqueue(callback);
    }

    public void xuatDSWatch() {
        arrWatchChitietdonhang.clear();
        int m=0;

        for(ObjectWatchChitietdonhang item : TrangThaiDonHang.arrWatchChitietdonhang)
        {
            objectWatchChitietdonhang = new ObjectWatchChitietdonhang();
//                        //objectWatchGiohang.setId(m);
            objectWatchChitietdonhang.set_id(item.get_id());
            objectWatchChitietdonhang.setIdWatch(item.getIdWatch());
            objectWatchChitietdonhang.setImgWatch(item.getImgWatch());
            objectWatchChitietdonhang.setPrice(item.getPrice());
            objectWatchChitietdonhang.setSoluong(item.getQuantity());
//                        objectWatchGiohang.setSoluong(1);
            objectWatchChitietdonhang.setWatchName(item.getWatchName());
            objectWatchChitietdonhang.setQuantity(item.getQuantity());

            objectWatchChitietdonhang.setWatchLine(item.getWatchLine());
            objectWatchChitietdonhang.setWatchBrand(item.getWatchBrand());
            objectWatchChitietdonhang.setType(item.getType());
            objectWatchChitietdonhang.setDescription(item.getDescription());

            arrWatchChitietdonhang.add(objectWatchChitietdonhang);
            m++;
        }
        watchChitietdonhangAdapter = new WatchChitietdonhangAdapter(ChiTietDonHang.this,R.layout.item_listview_chitietdonhang,arrWatchChitietdonhang);
        listviewWatch_Chitietdonhang.setAdapter(watchChitietdonhangAdapter);
        watchChitietdonhangAdapter.notifyDataSetChanged();

    }

    public static void updatePrice(){
        int sum=0;
        for (int i=0; i<arrWatchChitietdonhang.size(); i++)
            sum=sum+(arrWatchChitietdonhang.get(i).getPrice()*arrWatchChitietdonhang.get(i).getSoluong());
        sumPrice.setText("$"+sum);
        taxPrice.setText("$"+((float)(sum*10/100)));

        //totalPrice.setText(TrangThaiDonHang.totaldetail+"");
        totalPrice.setText("$"+(sum+((float)(sum*10/100))));
    }
}