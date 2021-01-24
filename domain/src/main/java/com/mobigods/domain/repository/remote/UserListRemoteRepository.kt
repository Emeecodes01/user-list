package com.mobigods.domain.repository.remote

import com.mobigods.domain.models.User

interface UserListRemoteRepository {
    suspend fun fetchUsers(): List<User>
    suspend fun fetchUser(userId: String): User
}
