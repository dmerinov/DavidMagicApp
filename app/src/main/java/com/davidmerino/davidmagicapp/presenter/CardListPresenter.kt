package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toCardDetailView
import com.davidmerino.davidmagicapp.mapper.toCardView
import com.davidmerino.davidmagicapp.model.CardDetailView
import com.davidmerino.davidmagicapp.model.CardView
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.repository.Repository

class CardListPresenter(
    errorHandler: ErrorHandler,
    view: CardListView,
    private val repository: Repository
) :
    Presenter<CardListView>(errorHandler, view) {
    override fun initialize() {
        getCards()
    }

    override fun resume() {
    }

    override fun stop() {
    }

    override fun destroy() {
    }

    fun onCardClick(card: Card) {
        view.navigateToCardDetailScreen(card.toCardDetailView())
    }

    private fun getCards() {
        repository.getCards(
            success = {
                view.showCards(it.map { it.toCardView() })
            },
            error = {
                error("Could not retrieve cards from network")
            }
        )
    }
}

interface CardListView : Presenter.View {
    fun navigateToCardDetailScreen(card: CardDetailView)
    fun showCards(cards: List<CardView>)

}