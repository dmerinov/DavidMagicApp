package com.davidmerino.davidmagicapp.view.activity

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.model.ShopView
import com.davidmerino.davidmagicapp.navigator.openDialPhone
import com.davidmerino.davidmagicapp.navigator.openGoogleMaps
import com.davidmerino.davidmagicapp.presenter.ShopListPresenter
import com.davidmerino.davidmagicapp.presenter.ShopListView
import com.davidmerino.davidmagicapp.view.adapter.ShopsAdapter
import kotlinx.android.synthetic.main.activity_card_list.*
import kotlinx.android.synthetic.main.activity_shop_list.*
import kotlinx.android.synthetic.main.item_shop.*
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
                getShopsUseCase = instance(),
                setFavouriteUseCase = instance()
            )
        }
    }

    private val shopsAdapter = ShopsAdapter(
        onItemClickListener = { presenter.onShopClick(it) },
        onPhoneClickListener = { presenter.onPhoneClick(it) },
        onFavClickListener = { geoPoint, isChecked ->
            presenter.onFavouriteClick(
                geoPoint,
                isChecked
            )
        }
    )

    override fun initializeUI() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        shops.apply {
            adapter = shopsAdapter
            layoutManager = LinearLayoutManager(this@ShopListActivity)
        }
    }

    override fun registerListeners() {
        searchShop.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // nothing to do
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            presenter.onTextChanged(s.toString())
        }

        override fun afterTextChanged(s: Editable?) {
            // nothing to do
        }

    }

    override fun showShops(shops: List<ShopView>) {
        shopsAdapter.replace(shops)
    }

    override fun callShop(phone: String) {
        openDialPhone(this, phone)
    }

    override fun showShopInMap(location: String) {
        openGoogleMaps(this, location)

    }

}