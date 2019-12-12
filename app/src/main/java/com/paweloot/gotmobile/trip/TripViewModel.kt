package com.paweloot.gotmobile.trip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paweloot.gotmobile.model.Point
import com.paweloot.gotmobile.model.PointRepository

class TripViewModel : ViewModel() {

    private val pointRepository = PointRepository()

    val points: LiveData<List<Point>> = pointRepository.getLiveData()

    fun filterPointList() {
        pointRepository.filterPointList()
    }

    var currentState = MutableLiveData<Int>(SELECT_START_POINT)
}

const val SELECT_START_POINT = 0
const val SELECT_END_POINT = 1
const val SELECT_VIA_POINT = 2
const val POINTS_SELECTED = 3
