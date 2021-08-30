package com.davidmerino.davidmagicapp.view.activity

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.View.*
import com.davidmerino.davidmagicapp.R
import com.davidmerino.davidmagicapp.extension.hideMe
import com.davidmerino.davidmagicapp.extension.load
import com.davidmerino.davidmagicapp.extension.showMe
import com.davidmerino.davidmagicapp.model.CardDetailView
import com.davidmerino.davidmagicapp.presenter.DetailCardPresenter
import com.davidmerino.davidmagicapp.presenter.DetailCardView
import kotlinx.android.synthetic.main.activity_detail_card.*
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
                view = this@DetailCardActivity,
                getCardByIdUseCase = instance()
            )
        }
    }

    override fun initializeUI() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun registerListeners() {
        showCard.setOnClickListener { presenter.onShowCardClick() }

    }

    override fun getCardId(): String {
        return intent.getStringExtra(CARD_DETAIL_ID_KEY)
            ?: throw IllegalArgumentException("This activity needs a card ID")
    }

    override fun showCard(card: CardDetailView) {
        nameValue.text = card.name
        cardPhoto.load(card.imageUrl)
        costValue.text = card.manaCost
        setValue.text = card.set
        toughnessValue.text = card.toughness
        powerValue.text = card.power
        descriptionText.text = card.text
    }

    override fun showImage(isVisible: Boolean) {
        when (isVisible) {
            true -> {
                showCard.text = resources.getString(R.string.hide_card)
                cardPhoto.showMe()
                shopName.hideMe()
                manaCost.hideMe()
                set.hideMe()
                toughness.hideMe()
                power.hideMe()
                nameValue.hideMe()
                costValue.hideMe()
                setValue.hideMe()
                toughnessValue.hideMe()
                powerValue.hideMe()
                description.hideMe()
                descriptionText.hideMe()
            }
            false -> {
                showCard.text = resources.getString(R.string.show_card)
                cardPhoto.hideMe()
                shopName.showMe()
                manaCost.showMe()
                set.showMe()
                toughness.showMe()
                power.showMe()
                nameValue.showMe()
                costValue.showMe()
                setValue.showMe()
                toughnessValue.showMe()
                powerValue.showMe()
                description.showMe()
                descriptionText.showMe()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}