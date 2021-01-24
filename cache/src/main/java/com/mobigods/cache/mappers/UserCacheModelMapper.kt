package com.mobigods.cache.mappers

import com.mobigods.cache.models.LocationCacheModel
import com.mobigods.cache.models.UserCacheModel
import com.mobigods.domain.base.BaseMapper
import com.mobigods.domain.models.User
import javax.inject.Inject

class UserCacheModelMapper @Inject constructor(
    private val locationCacheModelMapper: LocationCacheModelMapper
) : BaseMapper<UserCacheModel, User> {

    override fun mapTo(to: User): UserCacheModel {
        return UserCacheModel(
            email = to.email,
            firstName = to.firstName,
            id = to.id,
            lastName = to.lastName,
            picture = to.picture,
            title = to.title,
            registerDate = to.registerDate,
            gender = to.gender,
            dateOfBirth = to.dateOfBirth,
            location = locationCacheModelMapper.mapTo(to.location)
        )
    }

    override fun mapFrom(from: UserCacheModel): User {
        return User(
            email = from.email,
            firstName = from.firstName,
            id = from.id,
            lastName = from.lastName,
            picture = from.picture,
            title = from.title,
            registerDate = from.registerDate,
            gender = from.gender,
            dateOfBirth = from.dateOfBirth,
            location = locationCacheModelMapper.mapFrom(from.location ?: LocationCacheModel())
        )
    }
}
