package com.paweloot.gotmobile.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PointRepository {

    private val pointsLiveData = MutableLiveData<List<Point>>()

    fun getLiveData(): LiveData<List<Point>> {

        val points = listOf(
            Point(1, "Palenica Białczańska", 984, 1),
            Point(2, "Polana pod Wołoszynem", 1250, 1),
            Point(3, "Wierch Poroniec", 1101, 1),
            Point(4, "Dolina Filipka", 944, 1),
            Point(5, "Rusinowa Polana", 1210, 1),
            Point(6, "Łysa Polana", 970, 1),
            Point(7, "Wodogrzmoty Mickiewicza", 1100, 1),
            Point(8, "Schronisko PTTK w Roztoce", 1031, 1),
            Point(9, "Schronisko PTTK nad Morskim Okiem", 1406, 1),
            Point(10, "Siklawa", 1666, 1)
        )

        pointsLiveData.value = points

        return pointsLiveData
    }
}