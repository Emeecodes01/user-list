package com.mobigods.cache.impl

import com.mobigods.cache.db.dao.UserDao
import com.mobigods.cache.mappers.UserCacheModelMapper
import com.mobigods.cache.models.UserCacheModel
import com.mobigods.domain.models.User
import com.mobigods.domain.repository.cache.UserListCacheRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class UserListCacheRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userCacheModelMapper: UserCacheModelMapper
) : UserListCacheRepository {

    override suspend fun saveAllUsers(users: List<User>): List<Long> {
        return userDao.saveAllUsers(users.map { userCacheModelMapper.mapTo(it) })
    }

    override suspend fun saveUser(user: User) {
        userDao.updateUser(userCacheModelMapper.mapTo(user))
    }

    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers().flatMapConcat { users ->
            flowOf(users.map { userCacheModelMapper.mapFrom(it) })
        }
    }


    override fun getUser(userId: String): Flow<User> {
        return userDao.getUser(userId).flatMapConcat { value: UserCacheModel ->
            flowOf(userCacheModelMapper.mapFrom(value))
        }
    }

}