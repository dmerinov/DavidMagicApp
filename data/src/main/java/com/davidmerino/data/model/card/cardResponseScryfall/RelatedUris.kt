package com.davidmerino.data.model.card.cardResponseScryfall


import com.google.gson.annotations.SerializedName

data class RelatedUris(
    @SerializedName("edhrec")
    val edhrec: String,
    @SerializedName("gatherer")
    val gatherer: String,
    @SerializedName("mtgtop8")
    val mtgtop8: String,
    @SerializedName("tcgplayer_infinite_articles")
    val tcgplayerInfiniteArticles: String,
    @SerializedName("tcgplayer_infinite_decks")
    val tcgplayerInfiniteDecks: String
)