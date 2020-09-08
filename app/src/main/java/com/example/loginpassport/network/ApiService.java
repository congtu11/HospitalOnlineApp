package com.example.loginpassport.network;


import com.example.loginpassport.entities.AccessToken;
import com.example.loginpassport.model.BookCallModel;
import com.example.loginpassport.model.CartModel;
import com.example.loginpassport.model.DetailCartModel;
import com.example.loginpassport.model.Doctor_Model;
import com.example.loginpassport.model.MedicineModel;
import com.example.loginpassport.model.NewsModel;
import com.example.loginpassport.model.NotificationModel;
import com.example.loginpassport.model.UserModel;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    // Register User
    @POST("register")
    @FormUrlEncoded
    Call<AccessToken> register(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("c_password") String c_password);
    //Login User
    @POST("login")
    @FormUrlEncoded
    Call<AccessToken> login(@Field("username") String username,@Field("password") String password);
    //Refresh code but not use now
    @POST("refresh")
    @FormUrlEncoded
    Call<AccessToken> refresh(@Field("refresh_token") String refreshToken);
    //Check login splash
    @GET("checkLogins")
    Call<String> checkLogins();
    @GET("getAlldoctor")
    Call<List<Doctor_Model>> getAlldoctor();
    @GET("getDoctorbyId/{id}")
    Call<List<Doctor_Model>> getDoctorbyId(@Path("id") int id);
    @POST("logout")
    Call<String> logout();
    @GET("getDoctorbyCategory/{id}")
    Call<List<Doctor_Model>> getDoctorbyCategory(@Path("id") int id);
    //Book ticket
    @POST("bookticket")
    @FormUrlEncoded
    Call<String> bookticket(@Field("id_doctor") int id_doctor, @Field("fullname") String fullname, @Field("age") int age, @Field("sex") String sex, @Field("phone") int phone, @Field("date") String date, @Field("time") String time);
    //Get Notification
    @GET("getNotification")
    Call<List<NotificationModel>> getNotification();
    //Get all medicine
    @GET("getAllMedicine")
    Call<List<MedicineModel>> getAllMedicine();
    @GET("getMedicinebyCategory/{id}")
    Call<List<MedicineModel>> getMedicinebyCategory(@Path("id") int id);
    @POST("addtocart")
    @FormUrlEncoded
    Call<String> addtocart(@Field("id_medicine") int id_medicine,@Field("amount") int amount);
    @GET("getInforUser")
    Call<List<UserModel>> getInfoUser();
    @POST("bookvideocall")
    @FormUrlEncoded
    Call<String> bookvideocall(@Field("id_doctor") int id_doctor,@Field("date") String date,@Field("time") String time);
    @GET("getBookcallUser")
    Call<List<BookCallModel>> getBookcallUser();
    @GET("getmycart")
    Call<List<CartModel>> getMycart();
    @GET("getAmount/{id}")
    Call<List<DetailCartModel>> getAmount(@Path("id") int id);
    @POST("updateAmountcart")
    @FormUrlEncoded
    Call<String> updateAmountcart(@Field("id") int id, @Field("amount") int amount);
    //Checkout cart
    @GET("checkoutcart")
    Call<String> checkoutcart();
    @GET("getmycheckout")
    Call<List<CartModel>> getMycheckout();
    @POST("updateRecipients")
    @FormUrlEncoded
    Call<String> updateRecipients(@Field("address") String address, @Field("phone") String phone);
    @GET("getnews")
    Call<List<NewsModel>> getNews();



}
