package com.example.thenewsapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {
        private const val BASE_URL = "https://newsdata.io/api/1/"
        private var instance: Retrofit? = null

        fun getInstance(): Retrofit {
            if (instance == null) {
                synchronized(RetrofitClient::class.java) {
                    if (instance == null) {
                        instance = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                    }
                }
            }
            return instance!!
        }
    }
}