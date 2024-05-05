package com.example.dongho1.Activity.Activity1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import com.example.dongho1.Activity.API.RetrofitService;
import com.example.dongho1.Activity.API.SignInRequest;
import com.example.dongho1.Activity.API.SignInResponse;
import com.example.dongho1.Activity.InterfaceAPI.ApiService;
import com.example.dongho1.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhap extends AppCompatActivity {

    CardView nutTrangChu,nutNext;
    EditText phone, password;
    TextView txtSignup;
    public static String name,sophone,email,address,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        AnhXa();
        AddItem();

        nutTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TrangChu.class);
                v.getContext().startActivity(intent);
            }
        });

        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DangKy.class);
                v.getContext().startActivity(intent);
                //Toast.makeText(DangNhap.this, "dang ky", Toast.LENGTH_SHORT).show();
            }
        });

        ApiService apiService = RetrofitService.getRetrofit().create(ApiService.class);
        // bấm nút
        nutNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInRequest signInRequest = new SignInRequest(phone.getText().toString(), password.getText().toString());
                Call<SignInResponse> signInCall = apiService.signIn(signInRequest);
                // gài đk
                signInCall.enqueue(new Callback<SignInResponse>() {
                    @Override
                    public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                        if (response.isSuccessful()) {
                            SignInResponse signInResponse= response.body();
                            Intent intent = new Intent(v.getContext(), TrangChu.class);
                            v.getContext().startActivity(intent);
                            Toast.makeText(DangNhap.this, "Hello "+signInResponse.getCustomer().getName(), Toast.LENGTH_SHORT).show();
                            TrangChu.tokenUser = signInResponse.getToken();
                            name = signInResponse.getCustomer().getName();
                            email = signInResponse.getCustomer().getEmail();
                            sophone = signInResponse.getCustomer().getPhone();
                            address = signInResponse.getCustomer().getAddress();
                            pass = password.getText().toString();

                        } else {
                            Toast.makeText(DangNhap.this, "Incorrect account or password. Please try again!.", Toast.LENGTH_SHORT).show();

                            // handle error
                        }
                    }

                    @Override
                    public void onFailure(Call<SignInResponse> call, Throwable t) {
                        // handle failure
                    }
                });
            }
        });
    }

    private void AnhXa() {
        nutTrangChu = (CardView) findViewById(R.id.btnTrangChu);
        nutNext = (CardView) findViewById(R.id.btnNext_dangnhap);
        txtSignup = (TextView) findViewById(R.id.signup_dangnhap);
        phone = (EditText) findViewById(R.id.phone_dangnhap);
        password = (EditText) findViewById(R.id.password_dangnhap);

    }

    private void AddItem() {
        phone.setText("0832131109");
        password.setText("123");
    }

}