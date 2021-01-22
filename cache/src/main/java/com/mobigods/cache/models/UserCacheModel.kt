package com.mobigods.cache.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mobigods.domain.models.Location

@Entity(tableName = "users")
data class UserCacheModel(
    val email: String,
    val firstName: String,
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