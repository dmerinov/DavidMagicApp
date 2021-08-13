package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler

class DicePresenter(
    errorHandler: ErrorHandler,
    view: DiceView
) : Presenter<DiceView>(errorHandler, view) {
    override fun initialize() {
        view.initializeCounters(20)
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

    fun incrementCounter(amount: Int, player: Int) {
        var remaininglife = view.getRemainingLife(player)
        remaininglife += amount
        view.setLife(remaininglife.toString(), player)
    }

    fun decrementCounter(amount: Int, player: Int) {
        var remaininglife = view.getRemainingLife(player)
        remaininglife -= amount
        view.setLife(remaininglife.toString(), player)
    }
}

interface DiceView : Presenter.View {
    fun setLife(life: String, player: Int)
    fun initializeCounters(amount: Int)
    fun getRemainingLife(player: Int): Int
}