package com.davidmerino.domain.interactor.usecases

import com.davidmerino.domain.executor.Executor
import com.davidmerino.domain.interactor.SingleInteractor
import com.davidmerino.domain.repository.Repository

class GetLifeCounter(private val repository: Repository, executor: Executor) :
    SingleInteractor<Int>(executor = executor) {

    fun execute(onSuccess: (Int) -> Unit, onError: (Throwable) -> Unit, player: Int) {
        super.execute(onSuccess, onError, repository.getLifeCounter(player))
    }

}