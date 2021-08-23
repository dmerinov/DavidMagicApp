package com.davidmerino.domain.interactor

import com.davidmerino.domain.executor.Executor
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.repository.Repository

class GetCardsUseCase(private val repository: Repository, executor: Executor) :
    SingleInteractor<List<Card>>(executor = executor) {

    fun execute(onSuccess: (List<Card>) -> Unit, onError: (Throwable) -> Unit) {
        super.execute(onSuccess = onSuccess, onError = onError, repository.getCards())
    }

}