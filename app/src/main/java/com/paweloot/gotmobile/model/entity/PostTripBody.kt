package com.paweloot.gotmobile.model.entity

import java.util.*

data class PostTripBody(
    val userId: Int, val date: Date?,
    val gotPoints: Int, val pointsIds: List<Int>
)