package com.mobigods.domain.interactors.setup

import com.mobigods.domain.base.SuspendUseCase
import com.mobigods.domain.repository.cache.IDataStoreManager
import com.mobigods.domain.thread.ExecutionThread
import javax.inject.Inject

class GetUserListSetUpUseCase @Inject constructor(
    executionThread: ExecutionThread,
    private val dataStoreManager: IDataStoreManager
) : SuspendUseCase<Unit, Boolean>(executionThread) {

    override suspend fun execute(params: Unit?): Boolean {
        return dataStoreManager.isUserListSetUp
    }
}
