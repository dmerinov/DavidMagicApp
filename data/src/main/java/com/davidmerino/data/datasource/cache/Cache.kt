package com.davidmerino.data.datasource.cache

import io.reactivex.Single

interface Cache {
    fun getLife(player: Int): Single<Int>
    fun setLife(player: Int, amount: Int)
}