package com.mobigods.domain.repository.cache

import com.mobigods.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserListCacheRepository {
    suspend fun saveAllUsers(users: List<User>): List<Long>
    suspend fun saveUser(user: User)
    fun getAllUsers(): Flow<List<User>>
    suspend fun getUser(userId: String): User?
}