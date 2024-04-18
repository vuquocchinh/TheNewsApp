package com.example.thenewsapp.api

import com.example.thenewsapp.model.TopNewResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("news")
    fun fetchData(@Query("country") country:String,  @Query("apikey") apikey: String): Call<TopNewResponse>
}