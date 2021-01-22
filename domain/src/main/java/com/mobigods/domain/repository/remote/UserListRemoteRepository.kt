package com.mobigods.domain.repository.remote

import com.mobigods.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserListRemoteRepository {
    suspend fun fetchUsers(): List<User>
}