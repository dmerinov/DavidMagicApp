package com.davidmerino.data.datasource

import com.davidmerino.data.datasource.network.Network
import com.davidmerino.data.mapper.toCard
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.repository.Repository

class CommonRepository(private val network: Network) : Repository {
    override fun getCards(success: (List<Card>) -> Unit, error: () -> Unit) {
        network.getCards(
            success = {
                success(it.map { it.toCard() })
            },
            error = {
                error()
            }
        )
    }

}