package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toCardView
import com.davidmerino.davidmagicapp.model.CardView
import com.davidmerino.domain.interactor.usecases.GetCardsUseCase
import com.davidmerino.domain.model.Card

class CardListPresenter(
    private val getCardsUseCase: GetCardsUseCase,
    errorHandler: ErrorHandler,
    view: CardListView
) : Presenter<CardListView>(errorHandler, view) {

    private val cards: MutableList<Card> = mutableListOf()

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

    fun onTextChanged(text: String) {
        view.showCards(cards.filter { it.name.startsWith(text) }.map { it.toCardView() })
    }

    private fun getCards() {
        view.showProgress()
        getCardsUseCase.execute(
            onSuccess = {
                cards.addAll(it)
                view.showCards(cards.map { it.toCardView() })
                view.hideProgress()
            },
            onError = onError { view.showError(it) }
        )
    }
}

interface CardListView : Presenter.View {
    fun navigateToCardDetailScreen(id: String)
    fun showCards(cards: List<CardView>)

}