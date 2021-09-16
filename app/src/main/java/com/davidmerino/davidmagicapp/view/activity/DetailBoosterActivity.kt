package com.davidmerino.davidmagicapp.view.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.extension.load
import com.davidmerino.davidmagicapp.presenter.DetailBoosterPresenter
import com.davidmerino.davidmagicapp.presenter.DetailBoosterView
import com.davidmerino.domain.model.LocalPrice
import kotlinx.android.synthetic.main.activity_detail_booster.*
import kotlinx.android.synthetic.main.activity_detail_booster.cardPhoto
import kotlinx.android.synthetic.main.activity_detail_card.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class DetailBoosterActivity : RootActivity<DetailBoosterView>(), DetailBoosterView {

    companion object {
        private const val DETAIL_BOOSTER_KEY = "DETAIL_BOOSTER_KEY"
        private const val DETAIL_BOOSTER_IMAGE_KEY = "DETAIL_BOOSTER_IMAGE_KEY"
        fun getCallingIntent(context: Context, id: String, img: String) =
            Intent(context, DetailBoosterActivity::class.java).apply {
                putExtra(DETAIL_BOOSTER_KEY, id)
                putExtra(DETAIL_BOOSTER_IMAGE_KEY, img)
            }
    }

    override val progress: View
        get() = TODO("Not yet implemented")

    override val presenter: DetailBoosterPresenter by instance()

    override val layoutResourceId = R.layout.activity_detail_booster

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<DetailBoosterPresenter>() with provider {
            DetailBoosterPresenter(
                repository = instance(),
                errorHandler = instance(),
                executor = instance(),
                view = this@DetailBoosterActivity
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

    override fun getIntentId() = intent.getStringExtra(DETAIL_BOOSTER_KEY).toString()
    override fun getIntentImg() = intent.getStringExtra(DETAIL_BOOSTER_IMAGE_KEY).toString()

    override fun drawPrices(price: LocalPrice) {
        cardPhoto.load(getIntentImg())
        eurValue.text = price.eur
        usdValue.text = price.usd
        tixValue.text = price.tix
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}