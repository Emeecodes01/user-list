package com.mobigods.remote.impl

import com.mobigods.domain.models.User
import com.mobigods.domain.repository.cache.UserListCacheRepository
import com.mobigods.domain.repository.remote.UserListRemoteRepository
import com.mobigods.remote.mappers.UserRemoteModelMapper
import com.mobigods.remote.models.UserRemoteModel
import com.mobigods.remote.services.UserListService
import javax.inject.Inject

class UserListRemoteImpl @Inject constructor(
    private val service: UserListService,
    private val localRepository: UserListCacheRepository,
    private val userRemoteModelMapper: UserRemoteModelMapper
): UserListRemoteRepository {


    override suspend fun fetchUsers(): List<User> {
        val users = service.fetchUsers().data.map { userRemoteModelMapper.mapFrom(it) }
        localRepository.saveAllUsers(users)
        return users
    }

    override suspend fun fetchUser(userId: String): User {
        val user = userRemoteModelMapper.mapFrom(service.fetchUser(userId))
        localRepository.saveUser(user)
        return user
    }

}