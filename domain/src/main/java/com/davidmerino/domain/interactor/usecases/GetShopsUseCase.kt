package com.davidmerino.domain.interactor.usecases

import com.davidmerino.domain.executor.Executor
import com.davidmerino.domain.interactor.SingleInteractor
import com.davidmerino.domain.model.Shop
import com.davidmerino.domain.repository.Repository

class GetShopsUseCase(private val repository: Repository, executor: Executor) :
    SingleInteractor<List<Shop>>(executor = executor) {

    fun execute(onSuccess: (List<Shop>) -> Unit, onError: (Throwable) -> Unit) {
        super.execute(onSuccess = onSuccess, onError = onError, repository.getShops())
    }
}