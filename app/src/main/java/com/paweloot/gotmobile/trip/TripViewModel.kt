package com.paweloot.gotmobile.trip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paweloot.gotmobile.model.entity.Point
import com.paweloot.gotmobile.model.repository.PointRepository

const val SELECT_START_POINT = 0
const val SELECT_END_POINT = 1
const val SELECT_VIA_POINT = 2
const val POINTS_SELECTED = 3

class TripViewModel : ViewModel() {

    private val pointRepository =
        PointRepository()

    val points: LiveData<List<Point>> = pointRepository.getLiveData()

    fun filterPointList(pointFromId: Int) {
        pointRepository.filterPointList()
    }

    private val _selectedPoint = MutableLiveData<Point>()
    val selectedPoint: LiveData<Point> = _selectedPoint

    fun setSelectedPoint(point: Point) {
        _selectedPoint.value = point
    }

    var currentState = MutableLiveData<Int>(SELECT_START_POINT)

    private val _pathPoints: MutableList<Point> = mutableListOf()
    val pathPoints: List<Point> = _pathPoints

    fun addPathPoint(point: Point) {
        _pathPoints.add(point)
    }
}
