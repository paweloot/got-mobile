package com.paweloot.gotmobile.model.entity

import java.util.*

data class Trip(
    val id: Int,
    val tourist: Tourist,
    val date: Date?,
    val gotPoints: Int,
    val tripPoints: List<TripPoint>
)