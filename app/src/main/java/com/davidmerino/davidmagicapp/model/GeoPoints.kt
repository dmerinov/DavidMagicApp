package com.davidmerino.davidmagicapp.model

data class GeoPoints(
    val lat: Double,
    val long: Double,
    val name: String,
    val address: String,
    val city: String,
    val phone: String,
    val postalCode: Int
)

