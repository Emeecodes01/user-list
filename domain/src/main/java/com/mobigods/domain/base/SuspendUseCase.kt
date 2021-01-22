package ng.softcom.domain.base

import kotlinx.coroutines.withContext
import ng.softcom.domain.thread.ExecutionThread

abstract class SuspendUseCase<in PARAMS, out RESULT>(
    private val executionThread: ExecutionThread
) {

    suspend operator fun invoke(params: PARAMS? = null): RESULT {
        return withContext(executionThread.io()) {
            execute(params)
        }
    }


    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(params: PARAMS?): RESULT
}