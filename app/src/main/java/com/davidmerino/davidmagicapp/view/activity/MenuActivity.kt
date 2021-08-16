package com.davidmerino.davidmagicapp.view.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.extension.load
import com.davidmerino.davidmagicapp.navigator.navigateToCardListActivity
import com.davidmerino.davidmagicapp.navigator.navigateToDiceActivity
import com.davidmerino.davidmagicapp.navigator.navigateToSearchCardActivity
import com.davidmerino.davidmagicapp.presenter.ControlPanelView
import com.davidmerino.davidmagicapp.presenter.MenuPresenter
import kotlinx.android.synthetic.main.activity_main_menu.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider


class MenuActivity(
) : RootActivity<ControlPanelView>(), ControlPanelView {

    companion object {

        private const val MENU_ACTIVITY_KEY = "MENU_ACTIVITY_KEY"
        fun getCallingIntent2(context: Context) = Intent(context, MenuActivity::class.java)
    }

    override val progress: View
        get() = TODO("Not yet implemented")

    override val presenter: MenuPresenter by instance()

    override val layoutResourceId: Int = R.layout.activity_main_menu

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<MenuPresenter>() with provider {
            MenuPresenter(
                view = this@MenuActivity,
                errorHandler = instance()
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
        buyCard.setOnClickListener { presenter.onSearchCardClick() }
    }

    override fun navigateToCardListScreen() {
        navigateToCardListActivity(this)
    }

    override fun navigateToDiceScreen() {
        navigateToDiceActivity(this)
    }

    override fun navigateToSearchCardScreen() {
        navigateToSearchCardActivity(this)
    }

    override fun loadImageLogo(image: String) {
        logo.load(image)
    }

}