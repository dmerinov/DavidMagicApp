package com.davidmerino.davidmagicapp.view.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.presenter.ShopMapPresenter
import com.davidmerino.davidmagicapp.presenter.ShopMapView
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class ShopMapActivity : RootActivity<ShopMapView>(), ShopMapView {

    companion object {
        private const val SHOP_MAP_KEY = "SHOP_MAP_KEY"

        fun getCallingIntent(context: Context) =
            Intent(context, ShopMapActivity::class.java)
    }

    override val progress: View
        get() = TODO("Not yet implemented")

    override val presenter: ShopMapPresenter by instance()

    override val layoutResourceId: Int = R.layout.activity_search_shop

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<ShopMapPresenter>() with provider {
            ShopMapPresenter(
                errorHandler = instance(),
                view = this@ShopMapActivity
            )
        }
    }

    override fun initializeUI() {
        //nothing to do
    }

    override fun registerListeners() {
        //nothing to do
    }
}