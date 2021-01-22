package com.mobigods.domain.interactors

import com.mobigods.domain.base.SuspendUseCase
import com.mobigods.domain.models.User
import com.mobigods.domain.repository.remote.UserListRemoteRepository
import com.mobigods.domain.thread.ExecutionThread
import javax.inject.Inject

class GetAllRemoteUsersUseCase @Inject constructor (
    executionThread: ExecutionThread,
    private val remoteRepository: UserListRemoteRepository
): SuspendUseCase<Unit, List<User>>(executionThread) {


    override suspend fun execute(params: Unit?): List<User> {
        return remoteRepository.fetchUsers()
    }


}