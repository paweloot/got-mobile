package com.paweloot.gotmobile.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paweloot.gotmobile.api.RetrofitApi
import com.paweloot.gotmobile.model.entity.MtnGroup
import com.paweloot.gotmobile.model.entity.Point
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "PointRepository"

class PointRepository {

    private val _points = MutableLiveData<List<Point>>()
    val points: LiveData<List<Point>> = _points

    fun fetchPoints(mtnGroup: MtnGroup) {

        RetrofitApi.gotApi.getPoints(mtnGroup.id).enqueue(object : Callback<List<Point>> {
            override fun onFailure(call: Call<List<Point>>, t: Throwable) {
                Log.d(TAG, "onFailure: Failed to fetch Points: $t")
            }

            override fun onResponse(call: Call<List<Point>>, response: Response<List<Point>>) {
                _points.value = response.body()
            }
        })
    }
}