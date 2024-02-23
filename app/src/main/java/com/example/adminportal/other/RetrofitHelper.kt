package com.example.adminportal.other

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.SSLSocketFactory


object RetrofitHelper {

    val BASE_URL =  "http://13.201.186.160:3000/"
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder()
        //.sslSocketFactory(sslSocketFactory = SSLSocketFactory(), trustManager)
        .addInterceptor(loggingInterceptor).build()

    fun getInstance() = lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}