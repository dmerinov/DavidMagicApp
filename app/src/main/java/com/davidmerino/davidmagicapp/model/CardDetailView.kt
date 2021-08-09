package com.davidmerino.davidmagicapp.model

data class CardDetailView(
    val id: String,
    val imageUrl: String,
    val manaCost: String,
    val name: String,
    val set: String,
    val text: String,
    val toughness: String,
    val power: String,
)