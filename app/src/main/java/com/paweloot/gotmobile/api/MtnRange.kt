package com.paweloot.gotmobile.api

import com.squareup.moshi.Json

data class MtnRange(
    val id: Int,
    @Json(name = "nazwa_pasma") val name: String
)