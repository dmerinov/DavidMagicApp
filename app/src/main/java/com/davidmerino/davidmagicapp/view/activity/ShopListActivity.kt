package com.davidmerino.davidmagicapp.view.activity

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.model.GeoPoints
import com.davidmerino.davidmagicapp.presenter.ShopListPresenter
import com.davidmerino.davidmagicapp.presenter.ShopListView
import com.davidmerino.davidmagicapp.view.adapter.ShopsAdapter
import kotlinx.android.synthetic.main.activity_card_list.*
import kotlinx.android.synthetic.main.activity_shop_list.*
import kotlinx.android.synthetic.main.view_progress.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class ShopListActivity : RootActivity<ShopListView>(), ShopListView {
    companion object {
        fun getCallingIntent(context: Context) = Intent(context, ShopListActivity::class.java)
    }

    override val progress: View by lazy { progressView }

    override val presenter: ShopListPresenter by instance()

    override val layoutResourceId: Int = R.layout.activity_shop_list

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<ShopListPresenter>() with provider {
            ShopListPresenter(
                errorHandler = instance(),
                view = this@ShopListActivity,
                getShopsUseCase = instance()
            )
        }
    }

    private val shopsAdapter = ShopsAdapter()

    override fun initializeUI() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        shops.apply {
            adapter = shopsAdapter
            layoutManager = LinearLayoutManager(this@ShopListActivity)
        }
    }

    override fun registerListeners() {
        //nothing to do
    }

    override fun showShops(shops: List<GeoPoints>) {
        shopsAdapter.replace(shops.toMutableList())
    }

}