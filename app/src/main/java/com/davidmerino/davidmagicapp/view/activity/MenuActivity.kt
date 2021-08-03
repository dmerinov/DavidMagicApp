package com.davidmerino.davidmagicapp.view.activity

import android.view.View
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.presenter.MenuPresenter
import com.davidmerino.davidmagicapp.presenter.controlPanelView
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider


class MenuActivity(
) : RootActivity<controlPanelView>(), controlPanelView {

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
        //register the buttons listeners
    }

}