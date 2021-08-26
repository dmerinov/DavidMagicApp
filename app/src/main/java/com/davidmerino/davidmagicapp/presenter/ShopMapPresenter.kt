package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toGeoPoints
import com.davidmerino.davidmagicapp.model.GeoPoints
import com.davidmerino.domain.constants.Constants
import com.davidmerino.domain.interactor.usecases.GetShopsUseCase
import com.google.android.gms.maps.GoogleMap

class ShopMapPresenter(
    private val shopsUseCase: GetShopsUseCase,
    errorHandler: ErrorHandler,
    view: ShopMapView
) :
    Presenter<ShopMapView>(errorHandler, view) {

    var points: MutableList<GeoPoints> = mutableListOf()
    private val genX =
        GeoPoints(Constants.GEN_X_COORDINATES_LAT, Constants.GEN_X_COORDINATES_LONG, "Generación X")
    private val dadosF =
        GeoPoints(
            Constants.DADOS_FUERA_COORDINATES_LAT,
            Constants.DADOS_FUERA_COORDINATES_LONG,
            "Dados Fuera"
        )

    override fun initialize() {
        points.add(genX)
        points.add(dadosF)
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
        view.loadPoints(points, googleMap)
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