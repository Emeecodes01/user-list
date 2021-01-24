package com.mobigods.domain.interactors.users

import com.mobigods.domain.base.SuspendUseCase
import com.mobigods.domain.models.User
import com.mobigods.domain.repository.cache.UserListCacheRepository
import com.mobigods.domain.thread.ExecutionThread
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    executionThread: ExecutionThread,
    private val cacheRepository: UserListCacheRepository
) : SuspendUseCase<User, Unit>(executionThread) {

    override suspend fun execute(params: User?) {
        checkNotNull(params)
        cacheRepository.saveUser(params)
    }
}
