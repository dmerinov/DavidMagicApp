package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler


class DetailCardPresenter(errorHandler: ErrorHandler, view: DetailCardView) :
    Presenter<DetailCardView>(errorHandler, view) {

    override fun initialize() {
        println(view.getCardId())
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

interface DetailCardView : Presenter.View {
    fun getCardId(): String

}