package com.davidmerino.data.datasource.cache

class CommonCache() : Cache {

    private var lifeP1: Int = 0
    private var lifeP2: Int = 0
    override fun getLife(player: Int): Int =
        when (player) {
            0 -> lifeP1
            1 -> lifeP2
            else -> 0
        }

    override fun setLife(player: Int, amount: Int) {
        when (player) {
            0 -> lifeP1 = amount
            1 -> lifeP2 = amount
        }
    }


}