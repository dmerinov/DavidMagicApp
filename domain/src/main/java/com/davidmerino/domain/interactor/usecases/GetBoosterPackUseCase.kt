package com.davidmerino.domain.interactor.usecases

import com.davidmerino.domain.executor.Executor
import com.davidmerino.domain.interactor.SingleInteractor
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.repository.Repository

class GetBoosterPackUseCase(
    private val repository: Repository,
    executor: Executor
) : SingleInteractor<List<Card>>(executor = executor) {

    fun execute(expansion: String, onSuccess: (List<Card>) -> Unit, onError: (Throwable) -> Unit) {
        super.execute(
            onSuccess = onSuccess,
            onError = onError,
            repository.getBoosterPack(expansion)
        )
    }
    
}