package com.example.dongho1.Activity.Activity1;

import static android.graphics.Color.parseColor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.dongho1.Activity.API.RetrofitService;
import com.example.dongho1.Activity.InterfaceAPI.ApiService;
import com.example.dongho1.Activity.Model.Cart;
import com.example.dongho1.Activity.Model.Watch;
import com.example.dongho1.Activity.Object.ObjectWatchGiohang;
import com.example.dongho1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTiet extends AppCompatActivity {
    Context context;
    CardView nutTroVe, nutCart, nutAdd;
    TextView tvBrand,tvVerticalBrand, tvLine, tvType, watchName, price, description;
    ImageView imgWatch;

    ObjectWatchGiohang objectWatchGiohang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
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

        nutAdd.setOnClickListener(new View.OnClickListener()
        {
                @Override
                public void onClick(View v) {
                ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
                Call<Cart> call = apiService.getCart("Bearer "+TrangChu.tokenUser);
                call.enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        if (response.isSuccessful()) {
                            if(!TrangChu.getPrice.equals("SOLD OUT"))
                            {
                                GioHang.themWatchvaogio=true;

                                Intent intent = new Intent(v.getContext(), GioHang.class);
                                v.getContext().startActivity(intent);

                            }
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


    }
    public void AnhXa() {
        nutTroVe = (CardView) findViewById(R.id.btnTurnBack_chitiet);
        nutCart = (CardView) findViewById(R.id.btnCart);
        tvBrand= (TextView) findViewById(R.id.tv_brand_chitiet);
        tvLine= (TextView) findViewById(R.id.tv_line_chitiet);
        tvType= (TextView) findViewById(R.id.tv_type_chitiet);
        tvVerticalBrand= findViewById(R.id.tv_verticalBrand_chitiet);
        watchName= (TextView) findViewById(R.id.BigLineWatch_chitiet);
        price= (TextView) findViewById(R.id.Price_chitiet);
        description= (TextView) findViewById(R.id.MoTa_chitiet);
        imgWatch = (ImageView)findViewById(R.id.imgWatch_chitiet);
        nutAdd = (CardView) findViewById(R.id.nutAdd_chitiet);
    }

    public void AddItem() {

        tvBrand.setText(TrangChu.getBrandName);
        if(TrangChu.getBrandName.length() > 7)
            tvVerticalBrand.setTextSize(57);
        tvVerticalBrand.setText(TrangChu.getBrandName);

        tvLine.setText(TrangChu.getLineName);
        tvType.setText(TrangChu.getTypeName);
        watchName.setText(TrangChu.getWatchName);
        price.setText(TrangChu.getPrice);
        if(TrangChu.getPrice.equals("SOLD OUT"))
            price.setTextColor(parseColor("#F08080"));
        description.setText(TrangChu.getDescription);
        Picasso.get().load(TrangChu.getUrlImgWatch).into(imgWatch);

    }
}