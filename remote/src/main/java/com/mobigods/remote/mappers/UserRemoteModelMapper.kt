package com.mobigods.remote.mappers

import android.location.LocationManager
import com.mobigods.domain.models.Location
import com.mobigods.domain.models.User
import com.mobigods.remote.BaseMapper
import com.mobigods.remote.models.LocationRemoteModel
import com.mobigods.remote.models.UserRemoteModel
import javax.inject.Inject

class UserRemoteModelMapper @Inject constructor(
    private val locationRemoteMapper: LocationRemoteMapper
) : BaseMapper<UserRemoteModel, User> {
    override fun mapTo(to: User): UserRemoteModel {
        return UserRemoteModel(
            id = to.id,
            lastName = to.lastName,
            firstName = to.firstName,
            picture = to.picture,
            title = to.title,
            email = to.email,
            registerDate = to.registerDate,
            gender = to.gender,
            dateOfBirth = to.dateOfBirth,
            location = locationRemoteMapper.mapTo(to.location)
        )
    }


    override fun mapFrom(from: UserRemoteModel): User {
        return User(
            id = from.id,
            lastName = from.lastName,
            firstName = from.firstName,
            picture = from.picture,
            title = from.title,
            email = from.email,
            registerDate = from.registerDate ?: "",
            gender = from.gender ?: "",
            dateOfBirth = from.dateOfBirth ?: "",
            location = locationRemoteMapper.mapFrom(
                from.location ?: LocationRemoteModel()
            )
        )
    }

}