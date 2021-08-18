package com.davidmerino.data.model.card.cardResponseScryfall


import com.google.gson.annotations.SerializedName

data class CardResponseScryfallDto(
    @SerializedName("arena_id")
    val arenaId: Int,
    @SerializedName("artist")
    val artist: String,
    @SerializedName("artist_ids")
    val artistIds: List<String>,
    @SerializedName("booster")
    val booster: Boolean,
    @SerializedName("border_color")
    val borderColor: String,
    @SerializedName("card_back_id")
    val cardBackId: String,
    @SerializedName("cardmarket_id")
    val cardmarketId: Int,
    @SerializedName("cmc")
    val cmc: Double,
    @SerializedName("collector_number")
    val collectorNumber: String,
    @SerializedName("color_identity")
    val colorIdentity: List<String>,
    @SerializedName("colors")
    val colors: List<String>,
    @SerializedName("digital")
    val digital: Boolean,
    @SerializedName("edhrec_rank")
    val edhrecRank: Int,
    @SerializedName("flavor_text")
    val flavorText: String,
    @SerializedName("foil")
    val foil: Boolean,
    @SerializedName("frame")
    val frame: String,
    @SerializedName("full_art")
    val fullArt: Boolean,
    @SerializedName("games")
    val games: List<String>,
    @SerializedName("highres_image")
    val highresImage: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("illustration_id")
    val illustrationId: String,
    @SerializedName("image_status")
    val imageStatus: String,
    @SerializedName("image_uris")
    val imageUris: ImageUris,
    @SerializedName("keywords")
    val keywords: List<String>,
    @SerializedName("lang")
    val lang: String,
    @SerializedName("layout")
    val layout: String,
    @SerializedName("legalities")
    val legalities: Legalities,
    @SerializedName("mana_cost")
    val manaCost: String,
    @SerializedName("mtgo_id")
    val mtgoId: Int,
    @SerializedName("multiverse_ids")
    val multiverseIds: List<Int>,
    @SerializedName("name")
    val name: String,
    @SerializedName("nonfoil")
    val nonfoil: Boolean,
    @SerializedName("object")
    val objectX: String,
    @SerializedName("oracle_id")
    val oracleId: String,
    @SerializedName("oracle_text")
    val oracleText: String,
    @SerializedName("oversized")
    val oversized: Boolean,
    @SerializedName("power")
    val power: String,
    @SerializedName("preview")
    val preview: Preview,
    @SerializedName("prices")
    val prices: Prices,
    @SerializedName("prints_search_uri")
    val printsSearchUri: String,
    @SerializedName("promo")
    val promo: Boolean,
    @SerializedName("purchase_uris")
    val purchaseUris: PurchaseUris,
    @SerializedName("rarity")
    val rarity: String,
    @SerializedName("related_uris")
    val relatedUris: RelatedUris,
    @SerializedName("released_at")
    val releasedAt: String,
    @SerializedName("reprint")
    val reprint: Boolean,
    @SerializedName("reserved")
    val reserved: Boolean,
    @SerializedName("rulings_uri")
    val rulingsUri: String,
    @SerializedName("scryfall_set_uri")
    val scryfallSetUri: String,
    @SerializedName("scryfall_uri")
    val scryfallUri: String,
    @SerializedName("set")
    val `set`: String,
    @SerializedName("set_id")
    val setId: String,
    @SerializedName("set_name")
    val setName: String,
    @SerializedName("set_search_uri")
    val setSearchUri: String,
    @SerializedName("set_type")
    val setType: String,
    @SerializedName("set_uri")
    val setUri: String,
    @SerializedName("story_spotlight")
    val storySpotlight: Boolean,
    @SerializedName("tcgplayer_id")
    val tcgplayerId: Int,
    @SerializedName("textless")
    val textless: Boolean,
    @SerializedName("toughness")
    val toughness: String,
    @SerializedName("type_line")
    val typeLine: String,
    @SerializedName("uri")
    val uri: String,
    @SerializedName("variation")
    val variation: Boolean
)