package com.davidmerino.domain.model

data class Location(
    val address: String,
    val city: String,
    val lat: Double,
    val lng: Double,
    val name: String,
    val phone: String,
    val postalCode: Int
)