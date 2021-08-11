package com.davidmerino.data.datasource

import com.davidmerino.data.datasource.local.RealmDatabase
import com.davidmerino.data.datasource.network.Network
import com.davidmerino.data.mapper.toCard
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.repository.Repository

class CommonRepository(private val network: Network, private val realm: RealmDatabase) :
    Repository {
    override fun getCards(success: (List<Card>) -> Unit, error: () -> Unit) {
        network.getCards(
            success = {
                realm.setCards(it)
                success(it.map { it.toCard() })
            },
            error = {
                if (realm.hasCards()) {
                    success(realm.getCards().map { it.toCard() })
                } else {
                    error()
                }
            }
        )
    }

}