package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.domain.executor.Executor


class MockBoosterPresenter(
    errorHandler: ErrorHandler,
    view: MockBoosterCardView, executor: Executor
) : Presenter<MockBoosterCardView>(executor, errorHandler, view) {

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