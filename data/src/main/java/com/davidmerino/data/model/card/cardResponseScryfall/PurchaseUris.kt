package com.davidmerino.data.model.card.cardResponseScryfall


import com.google.gson.annotations.SerializedName

data class PurchaseUris(
    @SerializedName("cardhoarder")
    val cardhoarder: String,
    @SerializedName("cardmarket")
    val cardmarket: String,
    @SerializedName("tcgplayer")
    val tcgplayer: String
)