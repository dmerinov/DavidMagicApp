package com.davidmerino.data.datasource.preferences

import android.content.Context

class CommonPreferences(context: Context) : Preferences {

    companion object {
        private const val FAVOURITE_SHOPS_KEY = "FAVOURITE_SHOPS_KEY"
    }

    private val preferences = context.getSharedPreferences("favouriteShops", 0)

    override fun addFavouriteShop(id: Int) {
        val favourites = getFavourites().toMutableSet()
        favourites.add(id)
        putString(FAVOURITE_SHOPS_KEY, favourites.joinToString(","))
        println("addFavouriteShop--CommonPreferences--Executed")
    }

    override fun removeFavouriteShop(id: Int) {
        val favourites = getFavourites().toMutableSet()
        favourites.remove(id)
        putString(FAVOURITE_SHOPS_KEY, favourites.joinToString(","))
        println("removeFavouriteShop--CommonPreferences--Executed")

    }

    override fun getFavourites(): Set<Int> {
        println("getFavourites--CommonPreferences--Executed")
        val value = getString(FAVOURITE_SHOPS_KEY)
        return if (value.isNotEmpty()) {
            value.split(",").map { it.toInt() }.toSet()
        } else {
            emptySet()
        }
    }

    private fun putString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    private fun getString(key: String): String {
        return preferences.getString(key, "") ?: ""
    }

}