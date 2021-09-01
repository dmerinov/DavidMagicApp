package com.davidmerino.data.datasource.cache

import io.reactivex.Single

class CommonCache : Cache {

    private var lifeP1: Int = 0
    private var lifeP2: Int = 0

    override fun getLife(player: Int): Single<Int> =
        when (player) {
            0 -> Single.just(lifeP1)
            1 -> Single.just(lifeP2)
            else -> Single.just(0)
        }

    override fun setLife(player: Int, amount: Int) {
        when (player) {
            0 -> lifeP1 = amount
            1 -> lifeP2 = amount
        }
    }


}