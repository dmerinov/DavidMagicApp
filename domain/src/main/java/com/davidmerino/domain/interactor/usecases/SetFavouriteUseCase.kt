package com.davidmerino.domain.interactor.usecases

import com.davidmerino.domain.executor.Executor
import com.davidmerino.domain.interactor.CompletableInteractor
import com.davidmerino.domain.model.Shop
import com.davidmerino.domain.repository.Repository

class SetFavouriteUseCase(private val repository: Repository, executor: Executor) :
    CompletableInteractor(executor = executor) {

    fun execute(shop: Shop, onComplete: () -> Unit, onError: (Throwable) -> Unit) {
        super.execute(onComplete,onError,repository.setFavShop(shop))

    }
}