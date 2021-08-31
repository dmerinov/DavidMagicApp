package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toGeoPoints
import com.davidmerino.davidmagicapp.model.ShopView
import com.davidmerino.domain.interactor.usecases.GetShopsUseCase

class ShopMapPresenter(
    private val shopsUseCase: GetShopsUseCase,
    errorHandler: ErrorHandler,
    view: ShopMapView
) :
    Presenter<ShopMapView>(errorHandler, view) {

    override fun initialize() {
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

    fun onMapLoaded() {
        shopsUseCase.execute(
            onSuccess = {
                view.loadPoints(
                    points = it.map { it.toGeoPoints() },
                )
            },
            onError = { onError { it } }
        )


    }
}

interface ShopMapView : Presenter.View {
    fun loadPoints(points: List<ShopView>)
}