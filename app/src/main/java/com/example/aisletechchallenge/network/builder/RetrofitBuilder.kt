package com.example.aisletechchallenge.network.builder

import com.example.aisletechchallenge.network.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://app.aisle.co/V1/"

    private fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService : ApiService by lazy {
        provideRetrofit().create(ApiService::class.java)
    }

}