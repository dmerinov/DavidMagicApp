package com.davidmerino.data.model.card.cardResponseScryfall


import com.google.gson.annotations.SerializedName

data class Legalities(
    @SerializedName("brawl")
    val brawl: String,
    @SerializedName("commander")
    val commander: String,
    @SerializedName("duel")
    val duel: String,
    @SerializedName("future")
    val future: String,
    @SerializedName("gladiator")
    val gladiator: String,
    @SerializedName("historic")
    val historic: String,
    @SerializedName("historicbrawl")
    val historicbrawl: String,
    @SerializedName("legacy")
    val legacy: String,
    @SerializedName("modern")
    val modern: String,
    @SerializedName("oldschool")
    val oldschool: String,
    @SerializedName("pauper")
    val pauper: String,
    @SerializedName("paupercommander")
    val paupercommander: String,
    @SerializedName("penny")
    val penny: String,
    @SerializedName("pioneer")
    val pioneer: String,
    @SerializedName("premodern")
    val premodern: String,
    @SerializedName("standard")
    val standard: String,
    @SerializedName("vintage")
    val vintage: String
)