package com.mobigods.domain.interactors.users

import com.mobigods.domain.base.SuspendUseCase
import com.mobigods.domain.models.User
import com.mobigods.domain.repository.remote.UserListRemoteRepository
import com.mobigods.domain.thread.ExecutionThread
import javax.inject.Inject

class GetUserRemoteUseCase @Inject constructor (
    executionThread: ExecutionThread,
    private val remoteRepository: UserListRemoteRepository
): SuspendUseCase<String, Unit>(executionThread) {

    override suspend fun execute(params: String?) {
        checkNotNull(params)
        remoteRepository.fetchUser(params)
    }

}