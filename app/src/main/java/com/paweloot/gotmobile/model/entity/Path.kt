package com.paweloot.gotmobile.model.entity

import com.squareup.moshi.Json

data class Path(
    @Json(name = "id_punkt_z") val idFrom: Int,
    @Json(name = "id_punkt_do") val idTo: Int,
    @Json(name = "dlugosc") val length: Int,
    @Json(name = "roznica_wysokosci") val heightDiff: Int,
    @Json(name = "punkty_got") val gotPoints: Int,
    @Json(name = "punkty_got_powrot") val gotPointsReturn: Int
)