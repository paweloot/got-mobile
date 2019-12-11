package com.paweloot.gotmobile.model

import com.squareup.moshi.Json

data class MtnGroup(
    val id: Int,
    @Json(name = "kod_grupy") val code: String,
    @Json(name = "nazwa_grupy") val name: String,
    @Json(name = "id_pasma") val mtnRangeId: Int
)