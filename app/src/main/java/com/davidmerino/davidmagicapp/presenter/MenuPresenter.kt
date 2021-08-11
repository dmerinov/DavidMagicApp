package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler

class MenuPresenter (errorHandler: ErrorHandler, view: ControlPanelView) :
    Presenter<ControlPanelView>(errorHandler, view) {
    override fun initialize() {
        //nothing to do
    }

    fun onListCardClick(){
        view.navigateToCardListScreen()
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
interface ControlPanelView : Presenter.View{
    fun navigateToCardListScreen()
}