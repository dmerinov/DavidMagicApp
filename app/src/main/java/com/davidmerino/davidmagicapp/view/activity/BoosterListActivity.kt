package com.davidmerino.davidmagicapp.view.activity

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidmerino.data.datasource.CommonRepository
import com.davidmerino.data.datasource.local.RealmDatabase
import com.davidmerino.data.datasource.network.NetworkDataSource
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.model.CardView
import com.davidmerino.davidmagicapp.navigator.navigateToDetailCardActivity
import com.davidmerino.davidmagicapp.presenter.BoosterListPresenter
import com.davidmerino.davidmagicapp.presenter.BoosterListPresenterView
import com.davidmerino.davidmagicapp.view.adapter.BoosterAdapter
import kotlinx.android.synthetic.main.activity_booster_list.*
import kotlinx.android.synthetic.main.activity_main_menu.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class BoosterListActivity : RootActivity<BoosterListPresenterView>(), BoosterListPresenterView {
    companion object {
        private const val MOCK_DETAIL_EXP_KEY = "MOCK_DETAIL_EXP_KEY"

        fun getCallingIntent(context: Context, expName: String) =
            Intent(context, BoosterListActivity::class.java).apply {
                putExtra(MOCK_DETAIL_EXP_KEY, expName)
            }
    }

    override val progress: View
        get() = TODO("Not yet implemented")

    override val presenter: BoosterListPresenter by instance()

    override val layoutResourceId: Int = R.layout.activity_booster_list

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<BoosterListPresenter>() with provider {
            BoosterListPresenter(
                expansion = getExpansionName(),
                repository = CommonRepository(
                    network = NetworkDataSource(),
                    local = RealmDatabase(this@BoosterListActivity)
                ),
                errorHandler = instance(),
                view = this@BoosterListActivity
            )
        }
    }

    private val mockBoosterAdapter: BoosterAdapter = BoosterAdapter() { presenter.onCardClick(it) }

    override fun initializeUI() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        cards.apply {
            adapter = mockBoosterAdapter
            layoutManager = LinearLayoutManager(this@BoosterListActivity)
        }
    }

    override fun registerListeners() {
        //nothing to do
    }

    override fun navigateToBoosterDetailScreen(id: String) {
        navigateToDetailCardActivity(this, id)
    }

    override fun showCards(cards: List<CardView>) {
        mockBoosterAdapter.replace(cards.toMutableList())
    }

    override fun getExpansionName(): String {
        return intent.getStringExtra(MOCK_DETAIL_EXP_KEY)
            ?: throw IllegalArgumentException("This activity needs an expansion name")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}