package ng.softcom.domain.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ng.softcom.domain.thread.ExecutionThread

abstract class FlowUseCase<in Params, T> constructor(
    private val threadExecutor: ExecutionThread
){

    abstract fun buildFlowUseCase(params: Params? = null): Flow<T>

    fun execute(params: Params? = null): Flow<T> {
        return this.buildFlowUseCase(params)
            .flowOn(threadExecutor.io())
    }

}