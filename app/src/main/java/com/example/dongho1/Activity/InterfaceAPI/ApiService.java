package com.example.dongho1.Activity.InterfaceAPI;
//import com.example.dongho1.Activity.API.GetAllBrandsResponse;
import com.example.dongho1.Activity.API.ChangeIn4Request;
import com.example.dongho1.Activity.API.ItemIdRequest;
import com.example.dongho1.Activity.API.OrderIdRequest;
import com.example.dongho1.Activity.API.OrderResponse;
import com.example.dongho1.Activity.API.SignInRequest;
import com.example.dongho1.Activity.API.SignInResponse;
import com.example.dongho1.Activity.API.SignUpRequest;
import com.example.dongho1.Activity.API.SignUpResponse;
import com.example.dongho1.Activity.API.WatchIdRequest;
import com.example.dongho1.Activity.Model.Brand;
import com.example.dongho1.Activity.Model.Cart;
import com.example.dongho1.Activity.Model.Customer;
import com.example.dongho1.Activity.Model.Line;
import com.example.dongho1.Activity.Model.Watch;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ApiService {
    @POST("account/login")
    Call<SignInResponse> signIn(@Body SignInRequest request);

    //DangKy
    @POST("account/sign-up")
    Call<SignUpResponse> signUp(@Body SignUpRequest request);

    @GET("watch/get-all-brands")
    Call<List<Brand>> getallbrands();
    @GET("watch/get-all-watches")
    Call<List<Watch>> getAllwatchesofAllbrands();
    @GET("watch/get-2-watches-per-line-of-brand/{brandId}")
    Call<List<Watch>> get2watchesof1brand(@Path("brandId") String brandid);
    @GET("watch/get-lines-by-brand/{brandId}")
    Call<List<Line>> getLinesByBrand(@Path("brandId") String brandid);
    @GET("watch/get-watches-by-line/{lineId}")
    Call<List<Watch>> getWatchesByLine(@Path("lineId") String lineId);

    @GET("watch/get-watch-by-id/{watchId}")
    Call<Watch> getWatchById(@Path("watchId") String madongho);
    @GET("account/get-all")
    Call<ResponseBody> getAllCus(@Header("access-token-secret") String content_type);

    @GET("cart/get-cart")
    Call<Cart> getCart(@Header("Authorization") String authHeader);
    @POST("order/create-order")
    Call<ResponseBody> createOrder(@Header("Authorization") String authHeader);
    @GET("order/get-order")
    Call<OrderResponse> getOrder(@Header("Authorization") String authHeader);
    @POST("order/cancel-order/{orderId}")
    Call<ResponseBody> cancelOrder(@Header("Authorization") String authHeader,@Path("orderId") String orderId);
    @POST("cart/add-watch-to-cart")
    Call<Cart> addWatchToCart(@Header("Authorization") String authHeader,@Body WatchIdRequest watchId);
    @POST("cart/remove-watch-from-cart")
    Call<Cart> removeWatchFromCart(@Header("Authorization") String authHeader,@Body WatchIdRequest watchId);
    @POST("cart/remove-item-from-cart")
    Call<Cart> removeItemFromCart(@Header("Authorization") String authHeader,@Body ItemIdRequest itemId);

    @PUT("account/update-profile")
    Call<ResponseBody> updateProfile(@Header("Authorization") String authHeader, @Body ChangeIn4Request profile);
}

