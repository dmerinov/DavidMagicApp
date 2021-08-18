package com.davidmerino.data.model.card.cardResponseScryfall


import com.google.gson.annotations.SerializedName

data class ImageUris(
    @SerializedName("art_crop")
    val artCrop: String,
    @SerializedName("border_crop")
    val borderCrop: String,
    @SerializedName("large")
    val large: String,
    @SerializedName("normal")
    val normal: String,
    @SerializedName("png")
    val png: String,
    @SerializedName("small")
    val small: String
)