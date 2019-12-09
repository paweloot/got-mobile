package com.paweloot.gotmobile.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://url"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .build()

interface GotApiService {
    @GET("mtnranges")
    fun getMtnRanges():
            Call<String>
}

object GotApi {
    val retrofitService: GotApiService by lazy {
        retrofit.create(GotApiService::class.java)
    }
}