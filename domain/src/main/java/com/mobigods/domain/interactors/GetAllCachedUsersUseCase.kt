package com.mobigods.domain.interactors

import com.mobigods.domain.models.User
import com.mobigods.domain.repository.cache.UserListCacheRepository
import com.mobigods.domain.thread.ExecutionThread
import kotlinx.coroutines.flow.Flow
import ng.softcom.domain.base.FlowUseCase
import javax.inject.Inject

class GetAllCachedUsersUseCase @Inject constructor(
    executionThread: ExecutionThread,
    private val cacheRepository: UserListCacheRepository
) : FlowUseCase<Unit, List<User>>(executionThread) {


    override fun buildFlowUseCase(params: Unit?): Flow<List<User>> {
        return cacheRepository.getAllUsers()
    }


}