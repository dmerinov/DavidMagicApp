package com.davidmerino.davidmagicapp.view.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.navigator.openExternalUrl
import com.davidmerino.davidmagicapp.presenter.SearchCardPresenter
import com.davidmerino.davidmagicapp.presenter.SearchCardView
import kotlinx.android.synthetic.main.activity_detail_card.*
import kotlinx.android.synthetic.main.activity_search_card.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class SearchCardActivity : RootActivity<SearchCardView>(), SearchCardView {

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, SearchCardActivity::class.java)
    }

    override val progress: View
        get() = TODO("Not yet implemented")

    override val presenter: SearchCardPresenter by instance()

    override val layoutResourceId = R.layout.activity_search_card

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<SearchCardPresenter>() with provider {
            SearchCardPresenter(
                errorHandler = instance(),
                view = this@SearchCardActivity
            )
        }
    }

    override fun initializeUI() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun registerListeners() {
        search_card.setOnClickListener { presenter.onSearchClicked() }
    }

    override fun getCardName(): String = cardNameInput.text.toString()

    override fun openUrl(url: String) {
        openExternalUrl(this, url)
    }
}