package com.example.rxjava.data

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    fun buildRetrofit(): RetrofitInterface {
       return  Retrofit.Builder().baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RetrofitInterface::class.java)

    }
}