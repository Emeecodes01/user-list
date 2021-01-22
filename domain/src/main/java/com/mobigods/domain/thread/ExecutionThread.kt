package com.mobigods.domain.thread

import kotlinx.coroutines.CoroutineDispatcher

interface ExecutionThread {
    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
}