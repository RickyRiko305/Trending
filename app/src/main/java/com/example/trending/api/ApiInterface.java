package com.example.trending.api;

import com.example.trending.Items;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("repositories?language=python&since=daily&spoken_language_code=english")
    Call<List<Items>> getUserInfo();
}
