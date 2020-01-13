package com.paweloot.gotmobile.trip

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paweloot.gotmobile.api.RetrofitApi
import com.paweloot.gotmobile.model.entity.*
import com.paweloot.gotmobile.model.repository.PointRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

const val SELECT_START_POINT = 0
const val SELECT_END_POINT = 1
const val SELECT_VIA_POINT = 2
const val POINTS_SELECTED = 3
const val SAVE_TRIP = 4

private const val TAG = "TripViewModel"

class TripViewModel : ViewModel() {

    private val pointRepository = PointRepository()

    private val _selectedPoint = MutableLiveData<Point>()
    private val _pathPoints: MutableList<Point> = mutableListOf()
    private val _summaryPaths = MutableLiveData<List<SummaryPath>>()

    val points: LiveData<List<Point>> = pointRepository.points
    val selectedPoint: LiveData<Point> = _selectedPoint
    val summaryPaths: LiveData<List<SummaryPath>> = _summaryPaths
    var currentState = MutableLiveData<Int>(SELECT_START_POINT)

    fun fetchPoints(mtnGroup: MtnGroup) {
        pointRepository.fetchPoints(mtnGroup)
    }

    fun setSelectedPoint(point: Point) {
        _selectedPoint.value = point
    }

    fun addPathPoint(point: Point) {
        _pathPoints.add(point)
    }

    fun filterPoints() {
        pointRepository.filterPoints(selectedPoint.value!!)
    }

    fun fetchSummaryPaths() {
        RetrofitApi.gotApi.getSummaryPaths(_pathPoints.map { point -> point.id }).enqueue(object :
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

    fun saveTrip(loggedTourist: Tourist, selectedDate: Date, callback: (success: Boolean) -> Unit) {

        val gotPoints = summaryPaths.value!!.sumBy { path -> path.points }
        val pathPointsIds = _pathPoints.map { point -> point.id }

        val postTripBody = PostTripBody(
            loggedTourist.user.id,
            selectedDate,
            gotPoints,
            pathPointsIds
        )

        RetrofitApi.gotApi.saveTrip(postTripBody).enqueue(object : Callback<Trip> {
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
