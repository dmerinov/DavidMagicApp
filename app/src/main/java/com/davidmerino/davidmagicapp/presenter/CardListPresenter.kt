package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toCardView
import com.davidmerino.davidmagicapp.model.CardView
import com.davidmerino.domain.interactor.GetCardsUseCase

class CardListPresenter(
    private val getCardsUseCase: GetCardsUseCase,
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
        view.showProgress()
        getCardsUseCase.execute(
            onSuccess = {
                view.showCards(it.map { it.toCardView() })
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