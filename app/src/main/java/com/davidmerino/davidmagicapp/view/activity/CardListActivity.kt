package com.davidmerino.davidmagicapp.view.activity

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.model.CardView
import com.davidmerino.davidmagicapp.navigator.navigateToDetailCardActivity
import com.davidmerino.davidmagicapp.presenter.CardListPresenter
import com.davidmerino.davidmagicapp.presenter.CardListView
import com.davidmerino.davidmagicapp.view.adapter.CardsAdapter
import kotlinx.android.synthetic.main.activity_card_list.*
import kotlinx.android.synthetic.main.view_progress.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class CardListActivity() : RootActivity<CardListView>(), CardListView {
    companion object {
        fun getCallingIntent(context: Context) = Intent(context, CardListActivity::class.java)
    }

    override val progress: View by lazy { progressView }

    override val presenter: CardListPresenter by instance()

    override val layoutResourceId: Int = R.layout.activity_card_list

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<CardListPresenter>() with provider {
            CardListPresenter(
                view = this@CardListActivity,
                errorHandler = instance(),
                getCardsUseCase = instance()
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
        searchCard.addTextChangedListener(textWatcher)
    }

    override fun navigateToCardDetailScreen(id: String) {
        navigateToDetailCardActivity(this, id)
    }

    override fun showCards(cards: List<CardView>) {
        cardsAdapter.replace(cards.toMutableList())
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //nothing to do
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            presenter.onTextChanged(s.toString())
        }

        override fun afterTextChanged(s: Editable?) {
            //nothing to do
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}