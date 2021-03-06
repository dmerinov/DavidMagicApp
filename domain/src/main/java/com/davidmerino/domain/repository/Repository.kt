package com.davidmerino.domain.repository

import com.davidmerino.domain.model.Card


interface Repository {
    fun getCards(success: (List<Card>) -> Unit, error: () -> Unit)
}