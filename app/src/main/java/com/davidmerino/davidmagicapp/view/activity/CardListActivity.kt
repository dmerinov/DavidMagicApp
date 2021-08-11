package com.davidmerino.davidmagicapp.view.activity

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidmerino.data.datasource.CommonRepository
import com.davidmerino.data.datasource.local.RealmDatabase
import com.davidmerino.data.datasource.network.NetworkDataSource
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.model.CardView
import com.davidmerino.davidmagicapp.navigator.navigateToDetailCardActivity
import com.davidmerino.davidmagicapp.presenter.CardListPresenter
import com.davidmerino.davidmagicapp.presenter.CardListView
import com.davidmerino.davidmagicapp.view.adapter.CardsAdapter
import kotlinx.android.synthetic.main.activity_card_list.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class CardListActivity() : RootActivity<CardListView>(), CardListView {
    override val progress: View
        get() = TODO("Not yet implemented")

    override val presenter: CardListPresenter by instance()

    override val layoutResourceId: Int = R.layout.activity_card_list

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<CardListPresenter>() with provider {
            CardListPresenter(
                view = this@CardListActivity,
                errorHandler = instance(),
                repository = CommonRepository(
                    network = NetworkDataSource(),
                    realm = RealmDatabase(this@CardListActivity)
                )
            )
        }
    }

    private val cardsAdapter: CardsAdapter = CardsAdapter() { presenter.onCardClick(it) }

    override fun initializeUI() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        cards.apply {
            adapter = cardsAdapter
            layoutManager = LinearLayoutManager(this@CardListActivity)
        }

    }

    override fun registerListeners() {
        //nothing to do
    }

    override fun navigateToCardDetailScreen(id: String) {
        navigateToDetailCardActivity(this, id)
    }

    override fun showCards(cards: List<CardView>) {
        cardsAdapter.replace(cards.toMutableList())
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}