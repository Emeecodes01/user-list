package com.mobigods.remote.models

data class UserRemoteModel(
    val email: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val picture: String,
    val title: String,
    val registerDate: String?,
    val gender: String?,
    val dateOfBirth: String?,
    val location: LocationRemoteModel?
)
