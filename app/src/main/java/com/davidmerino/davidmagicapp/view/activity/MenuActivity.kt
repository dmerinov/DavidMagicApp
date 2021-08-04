package com.davidmerino.davidmagicapp.view.activity

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
        boosterPack.setOnClickListener { presenter.onBoosterClick() }
    }

    override fun navigateToBoosterScreen() {
        val intent = Intent(this, BoosterActivity::class.java)
        startActivity(intent)
    }

}