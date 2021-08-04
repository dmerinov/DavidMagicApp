package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler

class BoosterPresenter (errorHandler: ErrorHandler, view: BoosterView) :
Presenter<BoosterView>(errorHandler, view) {
    override fun initialize() {
        TODO("Not yet implemented")
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun destroy() {
        TODO("Not yet implemented")
    }

}

interface BoosterView : Presenter.View {

}