package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler

class MenuPresenter (errorHandler: ErrorHandler, view: ControlPanelView) :
    Presenter<ControlPanelView>(errorHandler, view) {
    override fun initialize() {

    }

    fun onListCardClick(){
        view.navigateToCardListScreen()
    }

    override fun resume() {
    }

    override fun stop() {
    }

    override fun destroy() {
    }

}
interface ControlPanelView : Presenter.View{
    fun navigateToCardListScreen()
}