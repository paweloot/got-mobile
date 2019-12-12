package com.paweloot.gotmobile.trip

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.paweloot.gotmobile.model.Point
import com.paweloot.gotmobile.model.PointRepository

class TripViewModel : ViewModel() {

    private val pointRepository = PointRepository()

    fun getPoints(): LiveData<List<Point>> {
        return pointRepository.getLiveData()
    }
}