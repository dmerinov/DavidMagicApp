package com.davidmerino.data.datasource

import com.davidmerino.data.datasource.local.Local
import com.davidmerino.data.datasource.network.Network
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.repository.Repository

class CommonRepository(private val network: Network, private val local: Local) :
    Repository {
    override fun getCards(success: (List<Card>) -> Unit, error: () -> Unit) {
        network.getCards(
            success = {
                local.setCards(it)
                success(it)
            },
            error = {
                if (local.hasCards()) {
                    success(local.getCards().map { it })
                } else {
                    error()
                }
            }
        )
    }

    override fun getCardByID(id: String, success: (Card) -> Unit, error: () -> Unit) {
        val obtainedCard = local.getCardByID(id)
        if (obtainedCard.id != "") {
            success(obtainedCard)
        } else {
            error()
        }
    }

}