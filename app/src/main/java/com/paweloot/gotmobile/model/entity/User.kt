package com.paweloot.gotmobile.model.entity

import java.util.*

data class User(
    val id: Int,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: Date?
)