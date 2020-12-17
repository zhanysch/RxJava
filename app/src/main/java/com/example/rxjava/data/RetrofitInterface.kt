package com.example.rxjava.data

import com.example.rxjava.data.model.SearchModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET(".")
    fun searchFilm(@Query("apikey")api: String,
                         @Query("s")s: String): Single<SearchModel>  //Single<SearchModel> это Rxjava singll не koin
}