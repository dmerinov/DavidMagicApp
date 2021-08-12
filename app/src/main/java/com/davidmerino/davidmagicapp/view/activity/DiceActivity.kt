package com.davidmerino.davidmagicapp.view.activity

import android.view.View
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.presenter.DicePresenter
import com.davidmerino.davidmagicapp.presenter.DiceView
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class DiceActivity : RootActivity<DiceView>(), DiceView {
    override val progress: View
        get() = TODO("Not yet implemented")

    override val presenter: DicePresenter by instance()

    override val layoutResourceId: Int = R.layout.activity_dice_counter

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<DicePresenter>() with provider {
            DicePresenter(
                errorHandler = instance(),
                view = this@DiceActivity
            )
            
        }
    }

    override fun initializeUI() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun registerListeners() {

    }

    override fun incrementCounter(id: Int) {
        TODO("Not yet implemented")
    }

    override fun decrementCounter(id: Int) {
        TODO("Not yet implemented")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}