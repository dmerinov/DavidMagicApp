package com.davidmerino.davidmagicapp.model

data class ShopView(
    val id: Int,
    val lat: Double,
    val long: Double,
    val name: String,
    val address: String,
    val city: String,
    val phone: String,
    val postalCode: Int,
    var isFavourite: Boolean
)

