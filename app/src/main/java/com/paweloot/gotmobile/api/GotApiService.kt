package com.paweloot.gotmobile.api

import com.paweloot.gotmobile.model.MtnRange
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://url"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GotApiService {
    @GET("mtnranges")
    fun getMtnRanges():
            Call<List<MtnRange>>
}

object GotApi {
    val retrofitService: GotApiService by lazy {
        retrofit.create(GotApiService::class.java)
    }
}