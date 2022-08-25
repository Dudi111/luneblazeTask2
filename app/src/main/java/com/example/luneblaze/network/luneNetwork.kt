package com.example.luneblaze.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object luneNetwork {

    val httploggingintercepter= HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private fun getretrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl( "https://luneblaze.com/").addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(httploggingintercepter).build()).build()
    }
    fun getapiservice()= getretrofit().create(ApiService::class.java)
}