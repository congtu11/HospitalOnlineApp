package com.example.loginpassport.Login;

import com.example.loginpassport.entities.ApiError;
import com.example.loginpassport.network.RetrofitBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;


public class Utils {
    public static  ApiError apiError;
    public static ApiError convertErrors(ResponseBody response){
        Converter<ResponseBody, ApiError> converter = RetrofitBuilder.getRetrofit().responseBodyConverter(ApiError.class, new Annotation[0]);



        try {
            apiError = converter.convert(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apiError;
    }
}