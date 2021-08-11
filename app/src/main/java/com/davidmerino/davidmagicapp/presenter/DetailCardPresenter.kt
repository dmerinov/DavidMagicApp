package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.domain.repository.Repository


class DetailCardPresenter(
    private val repository: Repository,
    errorHandler: ErrorHandler,
    view: DetailCardView
) :
    Presenter<DetailCardView>(errorHandler, view) {

    override fun initialize() {
        println(view.getCardId())
        loadCard(view.getCardId())
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

    private fun loadCard(id: String) {
        //repository.
    }
}

interface DetailCardView : Presenter.View {
    fun getCardId(): String

}