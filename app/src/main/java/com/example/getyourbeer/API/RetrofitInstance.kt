package com.shyptsolution.codingkaro.Contests.api

import com.shyptsolution.codingkaro.Contests.api.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitInstance {
    private val retrofit by lazy{
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES) // read timeout


        Retrofit.Builder().baseUrl(BASE_URL!!)
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .build()
    }
    val api:SimpleAPI by lazy {
        retrofit.create(SimpleAPI::class.java)
    }
}
