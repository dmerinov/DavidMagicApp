package com.davidmerino.domain.executor

import io.reactivex.Scheduler

/**
 * Executor
 */
interface Executor {
    fun new(): Scheduler// CoroutineDispatcher // bg

    fun main(): Scheduler
}