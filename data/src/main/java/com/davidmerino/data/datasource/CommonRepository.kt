package com.davidmerino.data.datasource

import com.davidmerino.data.datasource.local.Local
import com.davidmerino.data.datasource.network.Network
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.model.LocalPrices
import com.davidmerino.domain.repository.Repository
import io.reactivex.Single

class CommonRepository(private val network: Network, private val local: Local) :
    Repository {
    override fun getCards(): Single<List<Card>> =
        network.getCards()


    override fun getCardByID(id: String, success: (Card) -> Unit, error: () -> Unit) {
        val obtainedCard = local.getCardByID(id)
        if (obtainedCard.id != "") {
            success(obtainedCard)
        } else {
            error()
        }
    }

    override fun getBoosterPack(
        expansion: String,
        success: (List<Card>) -> Unit,
        error: () -> Unit
    ) {
        //to be completed
    }

    override fun getCardMarketInfo(
        multiverseId: String,
        success: (LocalPrices) -> Unit,
        error: () -> Unit
    ) {
        //to be completed
    }

}