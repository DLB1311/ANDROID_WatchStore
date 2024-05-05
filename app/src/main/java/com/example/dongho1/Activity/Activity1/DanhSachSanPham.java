package com.example.dongho1.Activity.Activity1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.dongho1.Activity.API.RetrofitService;
import com.example.dongho1.Activity.AdapterCustom.LineDsspAdapter;
import com.example.dongho1.Activity.AdapterCustom.WatchDsspAdapter;
import com.example.dongho1.Activity.InterfaceAPI.ApiService;
import com.example.dongho1.Activity.Model.Brand;
import com.example.dongho1.Activity.Model.Cart;
import com.example.dongho1.Activity.Model.Line;
import com.example.dongho1.Activity.Model.Watch;
import com.example.dongho1.Activity.Object.ObjectBrandDssp;

import com.example.dongho1.Activity.Object.ObjectLineDssp;
import com.example.dongho1.Activity.Object.ObjectWatchDssp;
import com.example.dongho1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachSanPham extends AppCompatActivity {
    CardView nutTroVe,nutCart;
    ImageButton nutSearch;
    Context context;
    ListView listviewLine_DSSP, listviewWatch_DSSP;

    public static ArrayList<ObjectBrandDssp> arrBrandDSSP;
    ObjectBrandDssp objectBrandDssp;
    LinearLayout llBrand;

    public static ArrayList<ObjectLineDssp> arrLineDSSP;
    ObjectLineDssp objectLineDssp;
    LineDsspAdapter lineDsspAdapter;

    public static ArrayList<ObjectWatchDssp> arrWatchDSSP;
    ObjectWatchDssp objectWatchDssp;
    WatchDsspAdapter watchDsspAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_san_pham);
        context=this;
        AnhXa();
        AddItem();
        System.out.println("sangsang238");

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
        // Header End---------------------------------------------------------------
    }


    public void AnhXa() {
        nutTroVe = (CardView) findViewById(R.id.btnTurnBackDSSP);
        nutSearch = (ImageButton) findViewById(R.id.searchbtnDSSP);
        nutCart = (CardView) findViewById(R.id.btnCart);
        arrLineDSSP = new ArrayList<>();
        listviewLine_DSSP = (ListView) findViewById(R.id.listviewLine_DSSP);
        arrWatchDSSP = new ArrayList<>();
        listviewWatch_DSSP = (ListView) findViewById(R.id.listviewWatch_DSSP);
        arrBrandDSSP = new ArrayList<>();
        llBrand = (LinearLayout) findViewById(R.id.llBrand_DSSP);
    }

    public void AddItem() {
        xuatDSBrand();
    }


    public void xuatDSBrand() {
        // Brand Start---------------------------------------------------------------
        ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
        Call<List<Brand>> call = apiService.getallbrands();
        call.enqueue(new Callback<List<Brand>>() {
            @Override
            public void onResponse(Call<List<Brand>> call, Response<List<Brand>> response) {
                if (response.isSuccessful()) {

                    List<Brand> brands = response.body();

                    for (Brand brand : brands) {
                        //Thêm các object brand ở đây.
                        objectBrandDssp = new ObjectBrandDssp();
                        objectBrandDssp.set_id(brand.getId());
                        objectBrandDssp.setImgBrand(brand.getLogo());
                        objectBrandDssp.setSlconlai(brand.getCountwatch());
                        objectBrandDssp.setBrandName(brand.getName());
                        arrBrandDSSP.add(objectBrandDssp);
                    }
                    // Brand Start---------------------------------------------------------------
                    LayoutInflater inflater = LayoutInflater.from(DanhSachSanPham.this);
                    ArrayList<View> views = new ArrayList<>();

                    for(int i=0; i< arrBrandDSSP.size();i++) {
                    views.add(inflater.inflate(R.layout.item_scrollview_brand_dssp, llBrand, false));
                    views.get(i).setTag(views.get(i).getId());
                    // Ánh xạ view
                    ImageView imgBrand = (ImageView) views.get(i).findViewById(R.id.img_brand_dssp);
                    TextView slconlai = (TextView) views.get(i).findViewById(R.id.slConLai_brand_dssp);
                    CardView cardBrand = (CardView) views.get(i).findViewById(R.id.card_brand_dssp);
                    CardView cardslConLai = (CardView) views.get(i).findViewById(R.id.card_slcl_dssp);
                    // Add items
                    String url =TrangChu.linkhttp+arrBrandDSSP.get(i).getImgBrand();
                    Picasso.get().load(url).into(imgBrand);
                    slconlai.setText(arrBrandDSSP.get(i).getSlconlai()+"");

                    cardBrand.setCardBackgroundColor(Color.parseColor("#404040"));
                    cardslConLai.setCardBackgroundColor(Color.parseColor("#8F8F8F"));

                    llBrand.addView(views.get(i));
                    int position=i;

                    //Nhấn
                    llBrand.getChildAt(position).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v){
                            for(int i=0; i<arrBrandDSSP.size();i++) {
                                views.add(inflater.inflate(R.layout.item_scrollview_brand_dssp, llBrand, false));
                                views.get(i).setTag(views.get(i).getId());
                                CardView cardBrand = (CardView) views.get(i).findViewById(R.id.card_brand_dssp);
                                CardView cardslConLai = (CardView) views.get(i).findViewById(R.id.card_slcl_dssp);
                                cardBrand.setCardBackgroundColor(Color.parseColor("#404040"));
                                cardslConLai.setCardBackgroundColor(Color.parseColor("#8F8F8F"));
                            }
                            cardBrand.setCardBackgroundColor(Color.parseColor("#616161"));
                            cardslConLai.setCardBackgroundColor(Color.parseColor("#C09E57"));
                            // thêm code này nọ ở đây.
                            TrangChu.getBrandName=arrBrandDSSP.get(position).getBrandName();
                            xuatDSLine(arrBrandDSSP.get(position).get_id());
                        }
                    });
                    }
                    // Brand End---------------------------------------------------------------
                } else {
                    Log.e("API6", "Error: " + response.code() + " - " + response.message());
                }
                // Mặc định chọn Brand đầu tiên
                llBrand.getChildAt(0).performClick();
            }
            @Override
            public void onFailure(Call<List<Brand>> call, Throwable t) {
                Log.e("API5", "Error: " + t.getMessage());
            }
        });

        // Brand End---------------------------------------------------------------
    }

    public void xuatDSLine(String brandSelected) {
        arrLineDSSP.clear();
        ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
        Call<List<Line>> call = apiService.getLinesByBrand(brandSelected);
        call.enqueue(new Callback<List<Line>>() {
            @Override
            public void onResponse(Call<List<Line>> call, Response<List<Line>> response) {
                if (response.isSuccessful()) {
                    List<Line> lines = response.body();
                    int m=0;
                    for (Line line : lines) {
                        //Thêm các object line ở đây.
                        objectLineDssp = new ObjectLineDssp();
                        objectLineDssp.setId(m);
                        objectLineDssp.set_id(line.getId());
                        objectLineDssp.setLine(line.getName());
                        arrLineDSSP.add(objectLineDssp);
                        m++;
                    }
                    // Line Start---------------------------------------------------------------
                    lineDsspAdapter = new LineDsspAdapter(DanhSachSanPham.this,R.layout.item_listview_line_dssp,arrLineDSSP);
                    listviewLine_DSSP.setAdapter(lineDsspAdapter);

                    // Select the first item by default
                    listviewLine_DSSP.post(new Runnable() {
                        @Override
                        public void run() {
                            View view = listviewLine_DSSP.getChildAt(0);
                            if (view != null) {
                                listviewLine_DSSP.performItemClick(view, 0, lineDsspAdapter.getItemId(0));
                            }
                        }
                    });

                    //Nhấn
                    listviewLine_DSSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            for(int i=0; i<DanhSachSanPham.arrLineDSSP.size();i++)
                            {
                                arrLineDSSP.get(i).setImgBar(0);
                            }
                            arrLineDSSP.get(position).setImgBar(R.drawable.rectangle_bar);
                            lineDsspAdapter.notifyDataSetChanged();
                            TrangChu.getLineName=arrLineDSSP.get(position).getLine();

                            xuatDSWatch(arrLineDSSP.get(position).get_id());
                        }
                    });
                    // Line End---------------------------------------------------------------


                } else {
                    Log.e("API4", "Error: " + response.code() + " - " + response.message());
                }

            }
            @Override
            public void onFailure(Call<List<Line>> call, Throwable t) {
                Log.e("API3", "Error: " + t.getMessage());
            }
        });
    }

    public void xuatDSWatch(String lineSelected) {
        arrWatchDSSP.clear();
        ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
        Call<List<Watch>> call = apiService.getWatchesByLine(lineSelected);

        call.enqueue(new Callback<List<Watch>>() {
            @Override
            public void onResponse(Call<List<Watch>> call, Response<List<Watch>> response) {
                if (response.isSuccessful()) {
                    List<Watch> watches = response.body();
                    int m=0;
                    for (Watch watch : watches) {
                        //Thêm các object watch ở đây.
                        objectWatchDssp = new ObjectWatchDssp();
                        objectWatchDssp.setId(m);
                        objectWatchDssp.set_id(watch.get_id());

                        objectWatchDssp.setImgWatch(watch.getImage());
                        objectWatchDssp.setPrice(watch.getPrice());
                        objectWatchDssp.setWatchName(watch.getName());
                        objectWatchDssp.setQuantity(watch.getQuantity());
                        objectWatchDssp.setWatchLine(watch.getLine().getName());
                        objectWatchDssp.setWatchBrand(watch.getLine().getBrand().getName());
                        objectWatchDssp.setType(watch.getType());
                        objectWatchDssp.setDescription(watch.getDescription());
                        arrWatchDSSP.add(objectWatchDssp);
                        m++;
                    }

                    // Watch Start---------------------------------------------------------------
                    watchDsspAdapter = new WatchDsspAdapter(DanhSachSanPham.this,R.layout.item_listview_watch_dssp,arrWatchDSSP);
                    listviewWatch_DSSP.setAdapter(watchDsspAdapter);
                    listviewWatch_DSSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                            //Truyền biến xuyên file: ở đây và adapter
                            TrangChu.getWatchName = arrWatchDSSP.get(position).getWatchName();
                            TrangChu.getTypeName = arrWatchDSSP.get(position).getType();
                            TrangChu.getDescription = arrWatchDSSP.get(position).getDescription();
                            TrangChu.getUrlImgWatch = TrangChu.linkhttp+arrWatchDSSP.get(position).getImgWatch();
                            if (arrWatchDSSP.get(position).getQuantity()==0)
                                TrangChu.getPrice="SOLD OUT";
                            else
                                TrangChu.getPrice=arrWatchDSSP.get(position).getPrice()+"";
                            TrangChu.getQuantity=arrWatchDSSP.get(position).getQuantity();
                            TrangChu.getIdCurWatch = arrWatchDSSP.get(position).get_id();
                            Intent intent = new Intent(v.getContext(), ChiTiet.class);
                            v.getContext().startActivity(intent);
                        }
                    });
                    // Watch End---------------------------------------------------------------
                }else {
                    Log.e("API2", "Error: " + response.code() + " - " + response.message());
                }
            }
            @Override
            public void onFailure(Call<List<Watch>> call, Throwable t) {
                Log.e("API1", "Error: " + t.getMessage());

            }
        });
    }
}