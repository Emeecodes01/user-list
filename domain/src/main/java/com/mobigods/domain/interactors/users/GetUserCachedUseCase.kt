package com.mobigods.domain.interactors.users

import com.mobigods.domain.base.SuspendUseCase
import com.mobigods.domain.models.User
import com.mobigods.domain.repository.cache.UserListCacheRepository
import com.mobigods.domain.thread.ExecutionThread
import javax.inject.Inject

class GetUserCachedUseCase @Inject constructor (
    executionThread: ExecutionThread,
    private val repository: UserListCacheRepository
): SuspendUseCase<String, User?>(executionThread){


    override suspend fun execute(params: String?): User? {
        checkNotNull(params)
        return repository.getUser(params)
    }
}