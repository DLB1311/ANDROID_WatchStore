package com.example.dongho1.Activity.Activity1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.dongho1.Activity.API.ChangeIn4Request;
import com.example.dongho1.Activity.API.RetrofitService;
import com.example.dongho1.Activity.InterfaceAPI.ApiService;
import com.example.dongho1.Activity.Model.Customer;
import com.example.dongho1.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuaThongTin extends AppCompatActivity {
    CardView nutNext,nutTroVe,nutTrangChu,nutLogOut;
    EditText password, address, name;
    TextView email, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thong_tin);
        AnhXa();
        AddItem();
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
        nutLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrangChu.tokenUser="";
                Intent intent = new Intent(v.getContext(), TrangChu.class);
                v.getContext().startActivity(intent);
            }
        });
        nutNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DangNhap.pass.equals(password.getText().toString())) {
                    ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
                    ChangeIn4Request profile = new ChangeIn4Request(name.getText().toString(),address.getText().toString());

                    // Gọi API update-profile
                    Call<ResponseBody> call = apiService.updateProfile("Bearer " + TrangChu.tokenUser, profile);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful())
                            {
                             AlertDialog.Builder thongbao = new AlertDialog.Builder((SuaThongTin) v.getContext());
                             thongbao.setTitle("Dialog");
                             thongbao.setMessage("Successful. Please login again!");
                             thongbao.setIcon(R.drawable.ic_thongbao);
                             TrangChu.tokenUser="";
                            thongbao.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        Intent intent = new Intent(v.getContext(), DangNhap.class);
                                        v.getContext().startActivity(intent);
                                    }
                                });
                                thongbao.show();
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(SuaThongTin.this, "LoiServer", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                else
                    password.setError("Incorrect Password. Please try again!");
            }
        });

    }
    public void AnhXa() {
        nutTroVe = (CardView) findViewById(R.id.btnTurnBack_suathongtin);
        nutTrangChu = (CardView) findViewById(R.id.btnTrangChu);
        email = (TextView) findViewById(R.id.email_suathongtin);
        password = (EditText) findViewById(R.id.password_suathongtin);
        phone = (TextView) findViewById(R.id.phone_suathongtin);
        address = (EditText) findViewById(R.id.address_suathongtin);
        name = (EditText) findViewById(R.id.name_suathongtin);
        nutNext = (CardView) findViewById(R.id.btnNext_suathongtin);
        nutLogOut = (CardView) findViewById(R.id.btnLogOut_suathongtin);
    }

    public void AddItem() {
        email.setText(DangNhap.email);
        password.setHint("Type your password!");
        phone.setText(DangNhap.sophone);
        address.setText(DangNhap.address);
        name.setText(DangNhap.name);
    }
}