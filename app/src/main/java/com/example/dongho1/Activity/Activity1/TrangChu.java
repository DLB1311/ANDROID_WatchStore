package com.example.dongho1.Activity.Activity1;

import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.Typeface;

import android.os.Bundle;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import com.example.dongho1.Activity.API.RetrofitService;

import com.example.dongho1.Activity.InterfaceAPI.ApiService;
import com.example.dongho1.Activity.Model.Brand;
import com.example.dongho1.Activity.Model.Cart;
import com.example.dongho1.Activity.Model.Watch;

import com.example.dongho1.Activity.Object.ObjectBrandTrangchu;
import com.example.dongho1.Activity.Object.ObjectWatchDssp;
import com.example.dongho1.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrangChu extends AppCompatActivity {
    CardView cardBanner, nutCart;
    ImageButton nutUser;
    TextView txtseemore;
    public static String tokenUser="";
    Context context;

    public static ArrayList<ObjectBrandTrangchu> arrBrandTrangchu;
    ObjectBrandTrangchu objectBrandTrangchu;
    LinearLayout llBrand;

    public static ArrayList<ObjectWatchDssp> arrWatchTrangchu;
    ObjectWatchDssp objectWatchTrangchu;
    LinearLayout llWatch;

    public static String getBrandName="", getLineName="",getTypeName="", getWatchName="",getPrice="",getDescription="",
            getUrlImgWatch="",
            getUrlImgBrand="",
            linkhttp = "http://10.0.2.2:6969/img/",
            getIdCurWatch="";
    public static int getQuantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        context=this;

        AnhXa();
        AddItem();


        // Header Start---------------------------------------------------------------
        nutCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("token",tokenUser);

                ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);

                Call<Cart> call = apiService.getCart("Bearer "+tokenUser);
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

        nutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("token",tokenUser);

                ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);

                Call<Cart> call = apiService.getCart("Bearer "+tokenUser);
                call.enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(v.getContext(), TrangThaiDonHang.class);
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

        cardBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DanhSachSanPham.class);
                v.getContext().startActivity(intent);

            }
        });
        txtseemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DanhSachSanPham.class);
                v.getContext().startActivity(intent);
            }
        });

    }

    public void AnhXa() {
        nutCart = (CardView) findViewById(R.id.btnCart);
        cardBanner = (CardView) findViewById(R.id.CardviewBanner_trangchu);
        txtseemore = (TextView) findViewById(R.id.txtSeemore_trangchu);
        arrBrandTrangchu = new ArrayList<>();
        llBrand = (LinearLayout) findViewById(R.id.llBrand_Trangchu);
        arrWatchTrangchu = new ArrayList<>();
        llWatch = (LinearLayout) findViewById(R.id.llWatch_Trangchu);
        nutUser=(ImageButton) findViewById(R.id.btnUserTrangChu);
    }

    public void AddItem() {
        xuatDSBrand();
    }

    public void xuatDSBrand() {
        ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
        Call<List<Brand>> call = apiService.getallbrands();
        call.enqueue(new Callback<List<Brand>>() {
            @Override
            public void onResponse(Call<List<Brand>> call, Response<List<Brand>> response) {
                if (response.isSuccessful()) {

                    List<Brand> brands = response.body();

                    for (Brand brand : brands) {
                        Log.d("Brand", "id: " + brand.getId() + ", name: " + brand.getName() + ", logoUrl: " + brand.getLogo());
                        objectBrandTrangchu = new ObjectBrandTrangchu();
                        objectBrandTrangchu.setId(brand.getId());
                        objectBrandTrangchu.setImgBrand(brand.getLogo());
                        objectBrandTrangchu.setBrandName(brand.getName()+"");
                        arrBrandTrangchu.add(objectBrandTrangchu);
                   }
                    // Brand Start---------------------------------------------------------------
                    LayoutInflater inflater = LayoutInflater.from(TrangChu.this);
                    ArrayList<View> views = new ArrayList<>();

                    for(int i=0; i< arrBrandTrangchu.size();i++) {

                        views.add(inflater.inflate(R.layout.item_scrollview_brand_trangchu, llBrand, false));
                        views.get(i).setTag(views.get(i).getId());
                        // Ánh xạ view
                        ImageView imgBrand = (ImageView) views.get(i).findViewById(R.id.ic_item_trangchu);
                        TextView brandName = (TextView) views.get(i).findViewById(R.id.txt_item_trangchu);
                        CardView cardBrand = (CardView) views.get(i).findViewById(R.id.cardview_item_trangchu);
                        ImageView imgBar = (ImageView) views.get(i).findViewById(R.id.choose_item_trangchu);
                        // Add items
                        getUrlImgBrand =linkhttp+arrBrandTrangchu.get(i).getImgBrand();
                        Picasso.get().load(getUrlImgBrand).into(imgBrand);
                        brandName.setText(arrBrandTrangchu.get(i).getBrandName());
                        imgBar.setImageResource(0);

                        llBrand.addView(views.get(i));
                        int position=i;
                        llBrand.getChildAt(position).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v){
                                for(int i=0; i<arrBrandTrangchu.size();i++) {
                                    views.add(inflater.inflate(R.layout.item_scrollview_brand_trangchu, llBrand, false));
                                    views.get(i).setTag(views.get(i).getId());
                                    ImageView imgBar = (ImageView) views.get(i).findViewById(R.id.choose_item_trangchu);
                                    CardView cardBrand = (CardView) views.get(i).findViewById(R.id.cardview_item_trangchu);
                                    imgBar.setImageResource(0);
                                    cardBrand.setCardBackgroundColor(Color.parseColor("#404040"));
                                }
                                imgBar.setImageResource(R.drawable.rectangle_bar);
                                cardBrand.setCardBackgroundColor(Color.parseColor("#616161"));
                                // thêm code này nọ ở đây.
                                getBrandName=arrBrandTrangchu.get(position).getBrandName();
                                xuatDSWatch(arrBrandTrangchu.get(position).getId());
                            }
                        });
                    }
                    // Brand End---------------------------------------------------------------
                } else {
                    Log.e("API", "Error: " + response.code() + " - " + response.message());
                }
                // Mặc định chọn Brand đầu tiên
                llBrand.getChildAt(0).performClick();
            }
            @Override
            public void onFailure(Call<List<Brand>> call, Throwable t) {
                Log.e("API", "Error: " + t.getMessage());
            }
        });
    }

    public void xuatDSWatch(String brandSelected) {
        arrWatchTrangchu.clear();
        ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
        Call<List<Watch>> call = apiService.get2watchesof1brand(brandSelected);
        call.enqueue(new Callback<List<Watch>>() {
            @Override
            public void onResponse(Call<List<Watch>> call, Response<List<Watch>> response) {
                List<Watch> watches = response.body();
                int m=0;
                for (Watch watch : watches) {
                    objectWatchTrangchu = new ObjectWatchDssp();
                    objectWatchTrangchu.setId(m);
                    objectWatchTrangchu.set_id(watch.get_id());
                    objectWatchTrangchu.setImgWatch(watch.getImage());
                    objectWatchTrangchu.setPrice(watch.getPrice());
                    objectWatchTrangchu.setWatchName(watch.getName());
                    objectWatchTrangchu.setQuantity(watch.getQuantity());
                    objectWatchTrangchu.setWatchLine(watch.getLine().getName());
                    objectWatchTrangchu.setWatchBrand(watch.getLine().getBrand().getName());
                    objectWatchTrangchu.setType(watch.getType());
                    objectWatchTrangchu.setDescription(watch.getDescription());
                    arrWatchTrangchu.add(objectWatchTrangchu);
                    m++;
                }


                // Watch Start---------------------------------------------------------------
                llWatch.removeAllViews();
                LayoutInflater inflater2 = LayoutInflater.from(TrangChu.this);
                ArrayList<View> views2 = new ArrayList<>();

                for(int i=0; i<arrWatchTrangchu.size();i++) {
                    views2.add(inflater2.inflate(R.layout.item_listview_watch_dssp, llWatch, false));
                    views2.get(i).setTag(views2.get(i).getId());
                    // Ánh xạ view
                    ImageView imgWatch = (ImageView) views2.get(i).findViewById(R.id.imgWatch_dssp);
                    TextView watchName = (TextView) views2.get(i).findViewById(R.id.Name_dssp);
                    TextView price = (TextView) views2.get(i).findViewById(R.id.Price_dssp);

                    // Add items
                    getUrlImgWatch =linkhttp+arrWatchTrangchu.get(i).getImgWatch();
                    Picasso.get().load(getUrlImgWatch).into(imgWatch);
                    watchName.setText(arrWatchTrangchu.get(i).getWatchName());
                    if(arrWatchTrangchu.get(i).getQuantity()==0) {
                        price.setText("SOLD OUT");
                        price.setTextColor(Color.parseColor("#F08080"));
                        price.setTypeface(null, Typeface.BOLD);
                    }
                    else
                        price.setText("$"+arrWatchTrangchu.get(i).getPrice());
                    llWatch.addView(views2.get(i));
                    int position=i;

                    // Nhấn
                    llWatch.getChildAt(position).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v){
                            //Truyền biến xuyên file
                            getIdCurWatch = arrWatchTrangchu.get(position).get_id();
                            getWatchName = arrWatchTrangchu.get(position).getWatchName();
                            getLineName = arrWatchTrangchu.get(position).getWatchLine();
                            getTypeName = arrWatchTrangchu.get(position).getType();
                            getDescription = arrWatchTrangchu.get(position).getDescription();
                            getQuantity = arrWatchTrangchu.get(position).getQuantity();
                            getUrlImgWatch = linkhttp+arrWatchTrangchu.get(position).getImgWatch();
                            if (arrWatchTrangchu.get(position).getQuantity()==0)
                                getPrice="SOLD OUT";
                            else
                                getPrice=arrWatchTrangchu.get(position).getPrice()+"";
                            Intent intent = new Intent(v.getContext(), ChiTiet.class);
                            v.getContext().startActivity(intent);

                        }
                    });
                }
                // Watch End---------------------------------------------------------------
            }
            @Override
            public void onFailure(Call<List<Watch>> call, Throwable t) {
            }
        });
    }
}