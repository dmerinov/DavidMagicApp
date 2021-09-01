package com.davidmerino.domain.interactor.usecases

import com.davidmerino.domain.executor.Executor
import com.davidmerino.domain.interactor.CompletableInteractor
import com.davidmerino.domain.repository.Repository

class SetLifeCounter(private val repository: Repository, executor: Executor) :
    CompletableInteractor(executor = executor) {

    fun execute(player: Int, life: Int) = repository.setLifeCounter(player, life)
}