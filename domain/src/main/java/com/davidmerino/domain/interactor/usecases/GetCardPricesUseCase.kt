package com.davidmerino.domain.interactor.usecases

import com.davidmerino.domain.executor.Executor
import com.davidmerino.domain.interactor.SingleInteractor
import com.davidmerino.domain.model.LocalPrices
import com.davidmerino.domain.repository.Repository

class GetCardPricesUseCase(
    private val repository: Repository,
    executor: Executor
) :
    SingleInteractor<LocalPrices>(executor = executor) {

    fun execute(
        multiverseId: String,
        success: (LocalPrices) -> Unit,
        error: (Throwable) -> Unit
    ) {
        super.execute(
            onSuccess = success,
            onError = error,
            repository.getCardMarketInfo(multiverseId)
        )
    }
}