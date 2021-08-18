package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toCardView
import com.davidmerino.davidmagicapp.model.CardView
import com.davidmerino.domain.repository.Repository


class BoosterListPresenter(
    private val repository: Repository,
    private val expansion: String,
    errorHandler: ErrorHandler, view: BoosterListPresenterView
) :
    Presenter<BoosterListPresenterView>(errorHandler, view) {
    override fun initialize() {
        getBooster()
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
        view.navigateToBoosterDetailScreen(card.id)
    }

    private fun getBooster() {
        repository.getBoosterPack(expansion,
            success = { view.showCards(it.map { it.toCardView() }) },
            error = { error("Could not retrieve cards from network") })
    }
}

interface BoosterListPresenterView : Presenter.View {
    fun navigateToBoosterDetailScreen(id: String)
    fun showCards(cards: List<CardView>)
    fun getExpansionName(): String
}