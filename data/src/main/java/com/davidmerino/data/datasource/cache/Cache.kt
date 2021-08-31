package com.davidmerino.data.datasource.cache

interface Cache {
    fun getLife(player: Int): Int
    fun setLife(player: Int, amount: Int)
}