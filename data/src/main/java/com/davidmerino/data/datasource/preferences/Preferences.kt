package com.davidmerino.data.datasource.preferences

interface Preferences {
    fun addFavouriteShop(id: Int)
    fun removeFavouriteShop(id: Int)
    fun getFavourites(): Set<Int>
}