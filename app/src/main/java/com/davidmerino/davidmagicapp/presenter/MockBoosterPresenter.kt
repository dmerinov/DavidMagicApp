package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.domain.repository.Repository


class MockBoosterPresenter(
    private val repository: Repository,
    errorHandler: ErrorHandler, view: MockBoosterCardView
) :
    Presenter<MockBoosterCardView>(errorHandler, view) {

    override fun initialize() {
        // nothing to do
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

    fun searchCard() {
        view.showBoosterPack()
    }
}

interface MockBoosterCardView : Presenter.View {
    fun getCardExpansion(): String
    fun showBoosterPack()
}