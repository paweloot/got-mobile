package com.paweloot.gotmobile.api

import com.paweloot.gotmobile.model.entity.MtnRange
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "http://192.168.43.130:8080/api/"

interface GotApi {

    @GET("mtnRanges")
    fun getMtnRanges(): Call<List<MtnRange>>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object RetrofitApi {
    val gotApi: GotApi by lazy {
        retrofit.create(GotApi::class.java)
    }
}