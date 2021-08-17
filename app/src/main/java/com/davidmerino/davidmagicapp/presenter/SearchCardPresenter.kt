package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler


class SearchCardPresenter(errorHandler: ErrorHandler, view: SearchCardView) :
    Presenter<SearchCardView>(errorHandler, view) {

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

interface SearchCardView : Presenter.View {

}