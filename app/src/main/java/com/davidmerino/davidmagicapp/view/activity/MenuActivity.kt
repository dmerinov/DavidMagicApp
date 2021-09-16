package com.davidmerino.davidmagicapp.view.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.extension.load
import com.davidmerino.davidmagicapp.navigator.*
import com.davidmerino.davidmagicapp.presenter.ControlPanelView
import com.davidmerino.davidmagicapp.presenter.MenuPresenter
import kotlinx.android.synthetic.main.activity_main_menu.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider


class MenuActivity : RootActivity<ControlPanelView>(), ControlPanelView {

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, MenuActivity::class.java)
    }

    override val progress: View
        get() = TODO("Not yet implemented")

    override val presenter: MenuPresenter by instance()

    override val layoutResourceId: Int = R.layout.activity_main_menu

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<MenuPresenter>() with provider {
            MenuPresenter(
                view = this@MenuActivity,
                errorHandler = instance(),
                executor = instance()
            )
        }
    }

    override fun initializeUI() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun registerListeners() {
        showCards.setOnClickListener { presenter.onListCardClick() }
        dices.setOnClickListener { presenter.onDiceClick() }
        buyCard.setOnClickListener { presenter.onBuyCardClick() }
        mockBooster.setOnClickListener { presenter.onBoosterCardClick() }
        shops.setOnClickListener { presenter.onShopSearchClick() }
        shopsList.setOnClickListener { presenter.onShopListClick() }
    }

    override fun navigateToCardListScreen() {
        navigateToCardListActivity(this)
    }

    override fun navigateToDiceScreen() {
        navigateToDiceActivity(this)
    }

    override fun navigateToBoosterPackScreen() {
        navigateToSearchBoosterActivity(this)
    }

    override fun navigateToBuyCardScreen() {
        navigateToBuyCardActivity(this)
    }

    override fun loadImageLogo(image: String) {
        logo.load(image)
    }

    override fun navigateToShopMapScreen() {
        navigateToShopMapScreen(this)
    }

    override fun navigateToShopListScreen() {
        navigateToShopListScreen(this)
    }

}