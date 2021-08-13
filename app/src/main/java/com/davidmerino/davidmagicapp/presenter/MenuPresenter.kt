package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler

class MenuPresenter(errorHandler: ErrorHandler, view: ControlPanelView) :
    Presenter<ControlPanelView>(errorHandler, view) {
    override fun initialize() {
        view.loadImageLogo("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Magicthegathering-logo.svg/2560px-Magicthegathering-logo.svg.png")
    }

    fun onListCardClick() {
        view.navigateToCardListScreen()
    }

    fun onDiceClick() {
        view.navigateToDiceScreen()
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

interface ControlPanelView : Presenter.View {
    fun navigateToCardListScreen()
    fun navigateToDiceScreen()
    fun loadImageLogo(image: String)
}