package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.model.CardView
import com.davidmerino.domain.repository.Repository

class CardListPresenter(
    private val repository: Repository,
    errorHandler: ErrorHandler,
    view: CardListView
) : Presenter<CardListView>(errorHandler, view) {

    override fun initialize() {
        getCards()
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

    fun onCardClick(card: CardView) {
        view.navigateToCardDetailScreen(card.id)
    }

    private fun getCards() {
        repository.getCards()
    }
}

interface CardListView : Presenter.View {
    fun navigateToCardDetailScreen(id: String)
    fun showCards(cards: List<CardView>)

}