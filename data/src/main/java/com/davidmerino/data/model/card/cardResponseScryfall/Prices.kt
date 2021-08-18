package com.davidmerino.data.model.card.cardResponseScryfall


import com.google.gson.annotations.SerializedName

data class Prices(
    @SerializedName("eur")
    val eur: String,
    @SerializedName("eur_foil")
    val eurFoil: String,
    @SerializedName("tix")
    val tix: String,
    @SerializedName("usd")
    val usd: String,
    @SerializedName("usd_foil")
    val usdFoil: String
)