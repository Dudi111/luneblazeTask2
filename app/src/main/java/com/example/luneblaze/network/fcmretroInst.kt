package com.example.luneblaze.network

import com.example.luneblaze.fcmdata.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class fcmretroInst {

    companion object{
        private val retro by lazy {
            Retrofit.Builder().baseUrl(Constants.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val fcmapi by lazy {
            retro.create(ApiService::class.java)
        }
    }
}