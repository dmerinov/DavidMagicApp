package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.domain.executor.Executor

class MenuPresenter(
    errorHandler: ErrorHandler,
    view: ControlPanelView,
    executor: Executor
) : Presenter<ControlPanelView>(executor, errorHandler, view) {

    override fun initialize() {
        view.loadImageLogo("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Magicthegathering-logo.svg/2560px-Magicthegathering-logo.svg.png")
    }

    fun onListCardClick() {
        view.navigateToCardListScreen()
    }

    fun onDiceClick() {
        view.navigateToDiceScreen()
    }

    fun onBuyCardClick() {
        view.navigateToBuyCardScreen()
    }

    fun onBoosterCardClick() {
        view.navigateToBoosterPackScreen()
    }

    fun onShopSearchClick() {
        view.navigateToShopMapScreen()
    }

    fun onShopListClick() {
        view.navigateToShopListScreen()
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

}

interface ControlPanelView : Presenter.View {
    fun navigateToCardListScreen()
    fun navigateToDiceScreen()
    fun navigateToBoosterPackScreen()
    fun navigateToBuyCardScreen()
    fun loadImageLogo(image: String)
    fun navigateToShopMapScreen()
    fun navigateToShopListScreen()
}