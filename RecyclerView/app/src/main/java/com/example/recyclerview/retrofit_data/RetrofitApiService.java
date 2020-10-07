package com.example.recyclerview.retrofit_data;

import com.example.recyclerview.model.ItemList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApiService {
    @GET("getItemsDB.php")
    Call<List<ItemList>> getItemsDB();
}
