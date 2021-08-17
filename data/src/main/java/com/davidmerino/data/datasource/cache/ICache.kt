package com.davidmerino.data.datasource.cache

interface ICache {
    fun getLife(player: Int): Int
    fun setLife(player: Int, amount: Int)
}