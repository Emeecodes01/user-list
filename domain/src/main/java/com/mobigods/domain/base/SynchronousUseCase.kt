package ng.softcom.domain.base

abstract class SynchronousUseCase<in Params, T> {
    abstract fun execute(params: Params? = null): T
}