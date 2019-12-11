package com.paweloot.gotmobile.model

import com.squareup.moshi.Json

data class Point(
    val id: Int,
    @Json(name = "nazwa_punktu") val name: String,
    @Json(name = "wysokosc") val height: Int,
    @Json(name = "id_grupy") val mtnGroupId: Int
)