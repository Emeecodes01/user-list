package com.mobigods.cache.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserCacheModel(
    val email: String,
    var firstName: String,
    @PrimaryKey
    val id: String,
    val lastName: String,
    val picture: String,
    val title: String,
    val registerDate: String = "",
    val gender: String = "",
    val dateOfBirth: String = "",

    @Embedded
    val location: LocationCacheModel?
)
