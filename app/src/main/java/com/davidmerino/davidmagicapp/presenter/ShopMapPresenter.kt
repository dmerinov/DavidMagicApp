package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toGeoPoints
import com.davidmerino.davidmagicapp.model.ShopView
import com.davidmerino.domain.executor.Executor
import com.davidmerino.domain.repository.Repository
import kotlinx.coroutines.*

class ShopMapPresenter(
    private val repository: Repository,
    errorHandler: ErrorHandler,
    view: ShopMapView,
    executor: Executor
) : Presenter<ShopMapView>(executor, errorHandler, view) {

    override fun initialize() {
        // nothing to do
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

    fun onMapLoaded() {
        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            val result = withContext(Dispatchers.IO) { repository.getShops() }
            result.fold(
                error = { onError { } },
                success = { view.loadPoints(it.map { it.toGeoPoints() }) }
            )
        }


    }
}

interface ShopMapView : Presenter.View {
    fun loadPoints(points: List<ShopView>)
}