package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toCardDetailView
import com.davidmerino.davidmagicapp.model.CardDetailView
import com.davidmerino.domain.interactor.usecases.GetCardByIdUseCase


class DetailCardPresenter(
    private val getCardByIdUseCase: GetCardByIdUseCase,
    errorHandler: ErrorHandler,
    view: DetailCardView
) : Presenter<DetailCardView>(errorHandler, view) {

    private var isShowingImage = false

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
        view.showCard(getCardByIdUseCase.execute(id).toCardDetailView())
    }

    fun onShowCardClick() {
        isShowingImage = !isShowingImage
        view.showImage(isShowingImage)
    }
}

interface DetailCardView : Presenter.View {
    fun getCardId(): String
    fun showCard(card: CardDetailView)
    fun showImage(isVisible: Boolean)

}