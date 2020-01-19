package com.paweloot.gotmobile.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paweloot.gotmobile.api.RestApi
import com.paweloot.gotmobile.model.entity.Point
import com.paweloot.gotmobile.model.entity.SummaryPath
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "SummaryPathRepository"

class SummaryPathRepository {

    private val _summaryPaths = MutableLiveData<List<SummaryPath>>()
    val summaryPaths: LiveData<List<SummaryPath>> = _summaryPaths

    fun fetchSummaryPaths(pathPoints: List<Point>) {
        RestApi.gotApi.getSummaryPaths(pathPoints.map { point -> point.id }).enqueue(object :
            Callback<List<SummaryPath>> {

            override fun onFailure(call: Call<List<SummaryPath>>, t: Throwable) {
                Log.d(TAG, "onFailure: Failed to fetch SummaryPaths: $t")
            }

            override fun onResponse(
                call: Call<List<SummaryPath>>,
                response: Response<List<SummaryPath>>
            ) {
                _summaryPaths.value = response.body()
            }
        })
    }
}