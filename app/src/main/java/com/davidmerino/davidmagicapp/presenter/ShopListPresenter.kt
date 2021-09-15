package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toGeoPoints
import com.davidmerino.davidmagicapp.mapper.toShop
import com.davidmerino.davidmagicapp.model.ShopView
import com.davidmerino.domain.interactor.usecases.SetFavouriteUseCase
import com.davidmerino.domain.repository.Repository
import kotlinx.coroutines.*

class ShopListPresenter(
    private val setFavouriteUseCase: SetFavouriteUseCase,
    private val repository: Repository,
    errorHandler: ErrorHandler,
    view: ShopListView
) : Presenter<ShopListView>(errorHandler, view) {

    private var shops: List<ShopView> = mutableListOf()

    override fun initialize() {
        getShops()
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

    private fun getShops() {
        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            val result = withContext(Dispatchers.IO) { repository.getShops() }
            result.fold(
                error = { onError { } },
                success = { view.showShops(it.map { it.toGeoPoints() }) }
            )
        }
    }

    fun onShopClick(shop: ShopView) {
        view.showShopInMap(
            "geo:".plus(
                shop.lat.toString().plus(", ").plus(shop.long).plus("?q=${shop.name}")
            )
        )
    }

    fun onPhoneClick(shop: ShopView) {
        view.callShop(shop.phone)
    }

    fun onTextChanged(text: String) {
        view.showShops(shops.filter { it.name.startsWith(text) })
    }

    fun onFavouriteClick(shopView: ShopView, isChecked: Boolean) {
        shopView.isFavourite = isChecked
        setFavouriteUseCase.execute(
            shop = shopView.toShop(),
            onComplete = {},
            onError = onError { view.showError(it) }
        )
    }
}

interface ShopListView : Presenter.View {
    fun showShops(cards: List<ShopView>)
    fun callShop(phone: String)
    fun showShopInMap(location: String)
}