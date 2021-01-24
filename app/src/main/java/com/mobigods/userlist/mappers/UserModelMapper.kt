package com.mobigods.userlist.mappers

import com.mobigods.domain.base.BaseMapper
import com.mobigods.domain.models.User
import com.mobigods.userlist.models.UserModel
import javax.inject.Inject

class UserModelMapper @Inject constructor(
    private val mapper: LocationModelMapper
) : BaseMapper<UserModel, User> {

    override fun mapTo(to: User): UserModel {
        return UserModel(
            email = to.email,
            firstName = to.firstName,
            id = to.id,
            lastName = to.lastName,
            picture = to.picture,
            title = to.title,
            registerDate = to.registerDate,
            gender = to.gender,
            dateOfBirth = to.dateOfBirth,
            location = mapper.mapTo(to.location)
        )
    }

    override fun mapFrom(from: UserModel): User {
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
            location = mapper.mapFrom(from.location)
        )
    }
}
