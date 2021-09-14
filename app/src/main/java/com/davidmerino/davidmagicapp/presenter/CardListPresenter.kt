package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toCardView
import com.davidmerino.davidmagicapp.model.CardView
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardListPresenter(
    private val repository: Repository,
    errorHandler: ErrorHandler,
    view: CardListView
) : Presenter<CardListView>(errorHandler, view) {

    private val cards: MutableList<Card> = mutableListOf()

    override fun initialize() {
        getCards()
    }

    override fun resume() {
        // nothing to do
    }

    override fun stop() {
        // nothing to do
    }

    override fun destroy() {
        // nothing to do
    }

    fun onCardClick(card: CardView) {
        view.navigateToCardDetailScreen(card.id)
    }

    fun onTextChanged(text: String) {
        view.showCards(cards.filter { it.name.startsWith(text) }.map { it.toCardView() })
    }

    private fun getCards() {
        CoroutineScope(Dispatchers.IO).launch {
            view.showProgress()
            val result = withContext(Dispatchers.IO) { repository.getCards() }
            view.hideProgress()
            result.fold(
                error = { onError { } },
                success = { view.showCards(it.map { it.toCardView() }) }
            )
        }
    }
}

interface CardListView : Presenter.View {
    fun navigateToCardDetailScreen(id: String)
    fun showCards(cards: List<CardView>)

}