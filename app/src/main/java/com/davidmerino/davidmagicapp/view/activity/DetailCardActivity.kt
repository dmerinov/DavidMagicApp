package com.davidmerino.davidmagicapp.view.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.presenter.DetailCardPresenter
import com.davidmerino.davidmagicapp.presenter.DetailCardView
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class DetailCardActivity : RootActivity<DetailCardView>(), DetailCardView {

    companion object {
        private const val CARD_DETAIL_ID_KEY = "CARD_DETAIL_ID_KEY"
        fun getCallingIntent(context: Context, id: String) =
            Intent(context, DetailCardActivity::class.java).apply {
                putExtra(CARD_DETAIL_ID_KEY, id)
            }
    }

    override val progress: View
        get() = TODO("Not yet implemented")

    override val presenter: DetailCardPresenter by instance()

    override val layoutResourceId: Int = R.layout.activity_detail_card

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<DetailCardPresenter>() with provider {
            DetailCardPresenter(
                errorHandler = instance(),
                view = this@DetailCardActivity
            )
        }
    }

    override fun initializeUI() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun registerListeners() {
        // nothing to do
    }

    override fun getCardId(): String {
        return intent.getStringExtra(CARD_DETAIL_ID_KEY)
            ?: throw IllegalArgumentException("This activity needs a card ID")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}