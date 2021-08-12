package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toCardDetailView
import com.davidmerino.davidmagicapp.model.CardDetailView
import com.davidmerino.domain.repository.Repository


class DetailCardPresenter(
    private val repository: Repository,
    errorHandler: ErrorHandler,
    view: DetailCardView
) :
    Presenter<DetailCardView>(errorHandler, view) {

    override fun initialize() {
        println(view.getCardId())
        getCardByID(view.getCardId())
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

    private fun getCardByID(id: String) {
        repository.getCardByID(
            id,
            success = { view.showCard(it.toCardDetailView()) },
            error = { error("could not retrieve card from bd") }
        )
    }
}

interface DetailCardView : Presenter.View {
    fun getCardId(): String
    fun showCard(card: CardDetailView)

}