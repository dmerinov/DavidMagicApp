package com.davidmerino.domain.interactor.usecases

import com.davidmerino.domain.executor.Executor
import com.davidmerino.domain.interactor.SingleInteractor
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.repository.Repository

class GetCardByIdUseCase(private val repository: Repository, executor: Executor) :
    SingleInteractor<List<Card>>(executor = executor) {

    fun execute(cardId: String) = repository.getCardByID(cardId)


}