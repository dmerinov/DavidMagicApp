package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.domain.interactor.usecases.GetCardPricesUseCase
import com.davidmerino.domain.model.LocalPrice

class DetailBoosterPresenter(
    private val getCardPricesUseCase: GetCardPricesUseCase,
    errorHandler: ErrorHandler,
    view: DetailBoosterView
) :
    Presenter<DetailBoosterView>(errorHandler, view) {

    override fun initialize() {
        getCardPrices(view.getIntentId())
    }

    override fun resume() {
        //nothing to do
    }

    override fun stop() {
        //nothing to do
    }

    override fun destroy() {
        //nothing to do
    }

    private fun getCardPrices(multiverseId: String) {

        getCardPricesUseCase.execute(
            multiverseId,
            success = {
                view.drawPrices(it)
            },
            error = { onError { it } }
        )

    }
}

interface DetailBoosterView : Presenter.View {
    fun getIntentId(): String
    fun drawPrices(price: LocalPrice)
    fun getIntentImg(): String
}