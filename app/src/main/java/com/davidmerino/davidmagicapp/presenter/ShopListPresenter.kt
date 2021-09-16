package com.davidmerino.davidmagicapp.presenter

import com.davidmerino.davidmagicapp.error.ErrorHandler
import com.davidmerino.davidmagicapp.mapper.toGeoPoints
import com.davidmerino.davidmagicapp.mapper.toShop
import com.davidmerino.davidmagicapp.model.ShopView
import com.davidmerino.domain.executor.Executor
import com.davidmerino.domain.repository.Repository
import kotlinx.coroutines.launch

class ShopListPresenter(
    private val repository: Repository,
    errorHandler: ErrorHandler,
    view: ShopListView,
    executor: Executor
) : Presenter<ShopListView>(executor, errorHandler, view) {

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
        scope.launch {
            execute { repository.getShops() }.fold(
                error = {
                    onError { }
                },
                success = {
                    view.showShops(it.map { it.toGeoPoints() })
                }
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
        repository.setFavShop(
            shop = shopView.toShop()
        )
    }
}

interface ShopListView : Presenter.View {
    fun showShops(cards: List<ShopView>)
    fun callShop(phone: String)
    fun showShopInMap(location: String)
}