package com.davidmerino.data.model.card.cardResponseScryfall


import com.google.gson.annotations.SerializedName

data class Preview(
    @SerializedName("previewed_at")
    val previewedAt: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("source_uri")
    val sourceUri: String
)