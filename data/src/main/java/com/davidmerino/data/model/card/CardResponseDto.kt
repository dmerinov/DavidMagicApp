package com.davidmerino.data.model.card


import com.google.gson.annotations.SerializedName

data class CardResponseDto(
    @SerializedName("cards") var cards: List<CardDto>
)

data class CardDto(
    @SerializedName("artist") var artist: String = "",
    @SerializedName("cmc") var cmc: Double = 0.0,
    @SerializedName("colorIdentity") var colorIdentity: List<String> = listOf(),
    @SerializedName("colors") var colors: List<String> = listOf(),
    @SerializedName("flavor") var flavor: String = "",
    @SerializedName("foreignNames") var foreignNames: List<ForeignName> = listOf(),
    @SerializedName("id") var id: String = "",
    @SerializedName("imageUrl") var imageUrl: String? = "",
    @SerializedName("layout") var layout: String,
    @SerializedName("legalities") var legalities: List<Legality> = listOf(),
    @SerializedName("manaCost") var manaCost: String = "",
    @SerializedName("multiverseid") var multiverseid: String?,
    @SerializedName("name") var name: String = "",
    @SerializedName("number") var number: String,
    @SerializedName("originalText") var originalText: String,
    @SerializedName("originalType") var originalType: String,
    @SerializedName("power") var power: String? = "",
    @SerializedName("printings") var printings: List<String> = listOf(),
    @SerializedName("rarity") var rarity: String,
    @SerializedName("rulings") var rulings: List<Ruling> = listOf(),
    @SerializedName("set") var `set`: String = "",
    @SerializedName("setName") var setName: String = "",
    @SerializedName("subtypes") var subtypes: List<String> = listOf(),
    @SerializedName("supertypes") var supertypes: List<String> = listOf(),
    @SerializedName("text") var text: String? = "",
    @SerializedName("toughness") var toughness: String? = "",
    @SerializedName("type") var type: String = "",
    @SerializedName("types") var types: List<String> = listOf(),
    @SerializedName("variations") var variations: List<String> = listOf()
)