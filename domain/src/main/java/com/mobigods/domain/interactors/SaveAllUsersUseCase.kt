package com.mobigods.domain.interactors

import com.mobigods.domain.base.SuspendUseCase
import com.mobigods.domain.models.User
import com.mobigods.domain.repository.cache.UserListCacheRepository
import com.mobigods.domain.repository.remote.UserListRemoteRepository
import com.mobigods.domain.thread.ExecutionThread
import javax.inject.Inject

class SaveAllUsersUseCase @Inject constructor(
    executionThread: ExecutionThread,
    private val cacheRepository: UserListCacheRepository
): SuspendUseCase<List<User>, Unit>(executionThread) {


    override suspend fun execute(params: List<User>?) {
        checkNotNull(params)
        cacheRepository.saveAllUsers(params)
    }


}