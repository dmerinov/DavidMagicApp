package com.davidmerino.domain.model

data class Card(
    val id: String,
    val imageUrl: String,
    val manaCost: String,
    val name: String,
    val set: String,
    val text: String,
    val toughness: String,
    val power: String,
)