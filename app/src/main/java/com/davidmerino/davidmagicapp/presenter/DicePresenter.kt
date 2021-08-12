package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toCardDetailView
import com.davidmerino.davidmagicapp.model.CardDetailView
import com.davidmerino.domain.repository.Repository

class DicePresenter(
    errorHandler: ErrorHandler,
    view: DiceView
) : Presenter<DiceView>(errorHandler, view) {
    override fun initialize() {
        //nothing to do
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

}

interface DiceView : Presenter.View {
    fun incrementCounter(id: Int)
    fun decrementCounter(id: Int)
}