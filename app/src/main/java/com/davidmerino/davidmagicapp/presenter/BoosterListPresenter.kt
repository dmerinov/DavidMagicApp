package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toCardView
import com.davidmerino.davidmagicapp.model.CardView
import com.davidmerino.domain.repository.Repository
import kotlinx.coroutines.*

class BoosterListPresenter(
    private val repository: Repository,
    private val expansion: String,
    errorHandler: ErrorHandler, view: BoosterListPresenterView
) : Presenter<BoosterListPresenterView>(errorHandler, view) {

    override fun initialize() {
        getBooster()
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
        view.navigateToBoosterDetailScreen(card.multiverseId, card.image)
    }

    private fun getBooster() {

        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            val result = withContext(Dispatchers.IO) { repository.getBoosterPack(expansion) }
            result.fold(
                error = { onError { } },
                success = { view.showCards(it.map { it.toCardView() }) }
            )
        }
    }
}

interface BoosterListPresenterView : Presenter.View {
    fun navigateToBoosterDetailScreen(id: String, img: String)
    fun showCards(cards: List<CardView>)
    fun getExpansionName(): String
}