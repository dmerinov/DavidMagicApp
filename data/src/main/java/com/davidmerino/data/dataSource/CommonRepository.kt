package com.davidmerino.data.dataSource

import com.davidmerino.data.network.Network
import com.davidmerino.domain.model.Card
import com.davidmerino.domain.repository.Repository

class CommonRepository(network: Network) : Repository {

    override fun getCards(success: (List<Card>) -> Unit, error: () -> Unit) {
        

    }
}