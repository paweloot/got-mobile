package com.paweloot.gotmobile.api

import com.paweloot.gotmobile.model.entity.MtnGroup
import com.paweloot.gotmobile.model.entity.MtnRange
import com.paweloot.gotmobile.model.entity.Point
import com.paweloot.gotmobile.model.entity.SummaryPath
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

const val BASE_URL = "http://192.168.43.130:8080/api/"

interface GotApi {

    @GET("mtnRanges")
    fun getMtnRanges(): Call<List<MtnRange>>

    @GET("mtnRanges/{mtnRangeId}/mtnGroups")
    fun getMtnGroups(@Path("mtnRangeId") mtnRangeId: Int):
            Call<List<MtnGroup>>

    @GET("mtnGroups/{mtnGroupId}/points")
    fun getPoints(@Path("mtnGroupId") mtnGroupId: Int):
            Call<List<Point>>

    @GET("points/pointsFrom/{pointFromId}")
    fun getFilteredPoints(@Path("pointFromId") pointFromId: Int):
            Call<List<Point>>

    @POST("summaryPaths")
    fun getSummaryPaths(@Body pointsIds: List<Int>):
            Call<List<SummaryPath>>
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