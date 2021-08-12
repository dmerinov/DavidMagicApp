package com.davidmerino.davidmagicapp.view.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.davidmerino.davidmagicapp.R
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

        fun getCallingIntent(context: Context, id: String): Intent {
            val intent = Intent(context, MenuActivity::class.java).apply {
                putExtra(MENU_ACTIVITY_KEY, id)
            }
            return intent
        }

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
    }

    override fun navigateToCardListScreen() {
        val intent = Intent(this, CardListActivity::class.java)
        startActivity(intent)
    }

    override fun navigateToDiceScreen() {
        val intent = Intent(this, DiceActivity::class.java)
        startActivity(intent)
    }

}