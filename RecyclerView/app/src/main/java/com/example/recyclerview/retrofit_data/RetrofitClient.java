package com.example.recyclerview.retrofit_data;

import com.example.recyclerview.retrofit_data.RetrofitApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    public static final String URL_BASE = "http://{tu_ip}:{tu_puerto}/";

    public static RetrofitApiService getApiService() {
        if (retrofit == null) {
            retrofit = new  Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(RetrofitApiService.class);
    }
}
