package com.davidmerino.data.model.shop


import com.google.gson.annotations.SerializedName

data class Site(
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("postalCode")
    val postalCode: Int
)