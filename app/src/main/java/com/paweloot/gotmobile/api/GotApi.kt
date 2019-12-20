package com.paweloot.gotmobile.api

import com.paweloot.gotmobile.model.entity.MtnRange
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "localhost:8080/api"

interface GotApi {

    @GET("/mtnranges")
    fun getMtnRanges(): Call<List<MtnRange>>
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object RetrofitApi {
    val gotApi: GotApi by lazy {
        retrofit.create(GotApi::class.java)
    }
}