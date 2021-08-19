package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.domain.model.LocalPrices
import com.davidmerino.domain.repository.Repository

class DetailBoosterPresenter(
    private val repository: Repository,
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
        println("EL ID DE LA CARTA A BUSCAR ES: $multiverseId")
        repository.getCardMarketInfo(
            multiverseId,
            success = { view.drawPrices(it) },
            error = { error("couldn't retrieve prices from network") }
        )
    }

}

interface DetailBoosterView : Presenter.View {
    fun getIntentId(): String
    fun drawPrices(prices: LocalPrices)
    fun getIntentImg(): String
}