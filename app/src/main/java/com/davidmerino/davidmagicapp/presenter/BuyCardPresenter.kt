package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler

class BuyCardPresenter(
    errorHandler: ErrorHandler,
    view: BuyCardView
) : Presenter<BuyCardView>(errorHandler, view) {

    companion object {
        private const val BASE_URL =
            "https://www.cardmarket.com/es/Magic/Products/Search?searchString="
    }

    override fun initialize() {
        // nothing to do
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

    fun onSearchClicked() {
        view.openUrl("$BASE_URL${view.getCardName()}")
    }
}

interface BuyCardView : Presenter.View {
    fun getCardName(): String
    fun openUrl(url: String)
}