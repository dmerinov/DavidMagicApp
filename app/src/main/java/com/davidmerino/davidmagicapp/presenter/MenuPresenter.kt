package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler

class MenuPresenter (errorHandler: ErrorHandler, view: controlPanelView) :
    Presenter<controlPanelView>(errorHandler, view) {
    override fun initialize() {

    }

    override fun resume() {
    }

    override fun stop() {
    }

    override fun destroy() {
    }

}
interface controlPanelView : Presenter.View{

}