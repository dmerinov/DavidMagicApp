package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toGeoPoints
import com.davidmerino.davidmagicapp.model.GeoPoints
import com.davidmerino.domain.interactor.usecases.GetShopsUseCase


class ShopListPresenter(
    private val getShopsUseCase: GetShopsUseCase,
    errorHandler: ErrorHandler,
    view: ShopListView
) :
    Presenter<ShopListView>(errorHandler, view) {
    override fun initialize() {
        getShops()
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

    private fun getShops() {
        view.showProgress()
        getShopsUseCase.execute(
            onSuccess = {
                view.showShops(it.map { it.toGeoPoints() })
            },
            onError {
                error("List could not be loaded properly, try again later")
            }
        )
        view.hideProgress()
    }

}

interface ShopListView : Presenter.View {
    fun showShops(cards: List<GeoPoints>)
}