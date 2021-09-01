package com.davidmerino.davidmagicapp.view.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.model.CardView
import com.davidmerino.davidmagicapp.navigator.navigateToBoosterListActivity
import com.davidmerino.davidmagicapp.presenter.MockBoosterCardView
import com.davidmerino.davidmagicapp.presenter.MockBoosterPresenter
import kotlinx.android.synthetic.main.activity_booster_expansion.*
import kotlinx.android.synthetic.main.item_booster.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class MockBoosterCardActivity : RootActivity<MockBoosterCardView>(), MockBoosterCardView {

    companion object {
        fun getCallingIntent(context: Context) =
            Intent(context, MockBoosterCardActivity::class.java)
    }

    override val progress: View
        get() = TODO("Not yet implemented")

    override val presenter: MockBoosterPresenter by instance()

    override val layoutResourceId: Int = R.layout.activity_booster_expansion

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<MockBoosterPresenter>() with provider {
            MockBoosterPresenter(
                getBoosterPackUseCase = instance(),
                errorHandler = instance(),
                view = this@MockBoosterCardActivity
            )
        }
    }

    override fun initializeUI() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun registerListeners() {
        searchExpansion.setOnClickListener { presenter.searchCard() }
    }

    override fun getCardExpansion(): String {
        return expansionName.text.toString()
    }

    override fun showBoosterPack(boosterPack: List<CardView>) {
        boosterPack.map { println("BOOSTERPACK ${it.title}") }
        navigateToBoosterListActivity(this, expansionName.text.toString())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}