package com.paweloot.gotmobile.trip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paweloot.gotmobile.model.entity.*
import com.paweloot.gotmobile.model.repository.PointRepository
import com.paweloot.gotmobile.model.repository.SummaryPathRepository
import com.paweloot.gotmobile.model.repository.TripRepository
import java.util.*

const val SELECT_START_POINT = 0
const val SELECT_END_POINT = 1
const val SELECT_VIA_POINT = 2
const val POINTS_SELECTED = 3
const val SAVE_TRIP = 4

private const val TAG = "TripViewModel"

class TripViewModel : ViewModel() {

    private val pointRepository = PointRepository()
    private val summaryPathRepository = SummaryPathRepository()
    private val tripRepository = TripRepository()

    private val _selectedPoint = MutableLiveData<Point>()
    private val _pathPoints: MutableList<Point> = mutableListOf()

    val points: LiveData<List<Point>> = pointRepository.points
    val summaryPaths: LiveData<List<SummaryPath>> = summaryPathRepository.summaryPaths

    val selectedPoint: LiveData<Point> = _selectedPoint
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
        summaryPathRepository.fetchSummaryPaths(_pathPoints)
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

        tripRepository.saveTrip(postTripBody) { success ->
            callback(success)
        }
    }
}
