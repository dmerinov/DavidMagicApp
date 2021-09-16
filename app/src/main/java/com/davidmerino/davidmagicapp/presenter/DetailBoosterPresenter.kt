package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.domain.executor.Executor
import com.davidmerino.domain.model.LocalPrice
import com.davidmerino.domain.repository.Repository
import kotlinx.coroutines.launch

class DetailBoosterPresenter(
    private val repository: Repository,
    errorHandler: ErrorHandler,
    view: DetailBoosterView,
    executor: Executor
) :
    Presenter<DetailBoosterView>(executor, errorHandler, view) {

    override fun initialize() {
        getCardPrices(view.getIntentId())
    }

    override fun resume() {
        // nothing to do
    }

    override fun stop() {
        // nothing to do
    }

    override fun destroy() {
        // nothing to do
    }

    private fun getCardPrices(multiverseId: String) {
        scope.launch {
            execute { repository.getCardMarketInfo(multiverseId) }.fold(
                error = { onError { } },
                success = { view.drawPrices(it) }
            )
        }
    }
    
}

interface DetailBoosterView : Presenter.View {
    fun getIntentId(): String
    fun drawPrices(price: LocalPrice)
    fun getIntentImg(): String
}