package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toGeoPoints
import com.davidmerino.davidmagicapp.model.GeoPoints
import com.davidmerino.domain.interactor.usecases.GetShopsUseCase
import com.google.android.gms.maps.GoogleMap

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

    fun onMapLoaded(googleMap: GoogleMap) {
        shopsUseCase.execute(
            onSuccess = {
                view.loadPoints(
                    points = it.map { it.toGeoPoints() },
                    googleMap = googleMap
                )
            },
            onError = { onError { it } }
        )


    }
}

interface ShopMapView : Presenter.View {
    fun loadPoints(points: List<GeoPoints>, googleMap: GoogleMap)
}