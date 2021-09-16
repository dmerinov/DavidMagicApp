package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toCardDetailView
import com.davidmerino.davidmagicapp.model.CardDetailView
import com.davidmerino.domain.executor.Executor
import com.davidmerino.domain.repository.Repository
import kotlinx.coroutines.launch

class DetailCardPresenter(
    private val repository: Repository,
    errorHandler: ErrorHandler,
    view: DetailCardView,
    executor: Executor
) : Presenter<DetailCardView>(executor, errorHandler, view) {

    private var isShowingImage = false

    override fun initialize() {
        println(view.getCardId())
        getCardByID(view.getCardId())
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

    private fun getCardByID(id: String) {
        scope.launch {
            execute {
                repository.getCardByID(id)
            }.fold(
                error = {},
                success = {
                    view.showCard(it.toCardDetailView())
                })
        }
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