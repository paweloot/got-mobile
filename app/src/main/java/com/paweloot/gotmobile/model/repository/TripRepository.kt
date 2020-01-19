package com.paweloot.gotmobile.model.repository

import android.util.Log
import com.paweloot.gotmobile.api.RestApi
import com.paweloot.gotmobile.model.entity.PostTripBody
import com.paweloot.gotmobile.model.entity.Trip
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "TripRepository"

class TripRepository {

    fun saveTrip(postTripBody: PostTripBody, callback: (Boolean) -> Unit) {
        RestApi.gotApi.saveTrip(postTripBody).enqueue(object : Callback<Trip> {
            override fun onFailure(call: Call<Trip>, t: Throwable) {
                Log.d(TAG, "onFailure: Failed to save the trip: $t")
                callback(false)
            }

            override fun onResponse(call: Call<Trip>, response: Response<Trip>) {
                Log.d(TAG, "onResponse: Successfully saved the trip!: ${response.body()}")
                callback(true)
            }
        })
    }
}