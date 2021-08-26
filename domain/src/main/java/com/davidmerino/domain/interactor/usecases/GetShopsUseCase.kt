package com.davidmerino.domain.interactor.usecases

import com.davidmerino.domain.executor.Executor
import com.davidmerino.domain.interactor.SingleInteractor
import com.davidmerino.domain.model.Location
import com.davidmerino.domain.repository.Repository

class GetShopsUseCase(private val repository: Repository, executor: Executor) :
    SingleInteractor<List<Location>>(executor = executor) {

    fun execute(onSuccess: (List<Location>) -> Unit, onError: (Throwable) -> Unit) {
        super.execute(onSuccess = onSuccess, onError = onError, repository.getShops())
    }
}