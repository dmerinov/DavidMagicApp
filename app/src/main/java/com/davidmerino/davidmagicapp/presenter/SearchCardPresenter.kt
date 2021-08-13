package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler

class SearchCardPresenter(
    errorHandler: ErrorHandler,
    view: SearchCardView
) : Presenter<SearchCardView>(errorHandler, view) {

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

interface SearchCardView : Presenter.View {
    fun getCardName(): String
    fun openUrl(url: String)
}