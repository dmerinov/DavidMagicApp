package com.davidmerino.davidmagicapp.view.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.presenter.DicePresenter
import com.davidmerino.davidmagicapp.presenter.DiceView
import com.davidmerino.davidmagicapp.presenter.Player
import kotlinx.android.synthetic.main.activity_dice_counter.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class DiceActivity : RootActivity<DiceView>(), DiceView {

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, DiceActivity::class.java)
    }

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

        addLifeP1.setOnClickListener { presenter.incrementCounter(Player.PLAYER_1) }
        addLifeP2.setOnClickListener { presenter.incrementCounter(Player.PLAYER_2) }
        decrementLifeP1.setOnClickListener { presenter.decrementCounter(Player.PLAYER_1) }
        decrementLifeP2.setOnClickListener { presenter.decrementCounter(Player.PLAYER_2) }


    }

    override fun showLife(life: String, player: Player) {
        when (player) {
            Player.PLAYER_1 -> lifeCounterP1.text = life
            Player.PLAYER_2 -> lifeCounterP2.text = life
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}