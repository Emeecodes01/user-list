package com.mobigods.domain.interactors.users

import com.mobigods.domain.base.FlowUseCase
import com.mobigods.domain.models.User
import com.mobigods.domain.repository.cache.UserListCacheRepository
import com.mobigods.domain.thread.ExecutionThread
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllCachedUsersUseCase @Inject constructor(
    executionThread: ExecutionThread,
    private val cacheRepository: UserListCacheRepository
) : FlowUseCase<Unit, List<User>>(executionThread) {

    override fun buildFlowUseCase(params: Unit?): Flow<List<User>> {
        return cacheRepository.getAllUsers()
    }
}
