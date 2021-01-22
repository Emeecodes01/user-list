package ng.softcom.domain.base

import kotlinx.coroutines.withContext
import ng.softcom.domain.thread.ExecutionThread

abstract class NoResultSuspendUseCase<in P>(
    private val executionThread: ExecutionThread
) {

    suspend operator fun invoke(params: P? = null) {
        withContext(executionThread.io()) {
            execute(params)
        }
    }


    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(params: P?)
}