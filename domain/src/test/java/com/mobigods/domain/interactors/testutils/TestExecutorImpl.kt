package com.mobigods.domain.interactors.testutils

import com.mobigods.domain.thread.ExecutionThread
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

class TestExecutionThreadImpl : ExecutionThread {
    override fun io(): CoroutineDispatcher = TestCoroutineDispatcher()
    override fun ui(): CoroutineDispatcher = TestCoroutineDispatcher()
    override fun default(): CoroutineDispatcher = TestCoroutineDispatcher()
}
