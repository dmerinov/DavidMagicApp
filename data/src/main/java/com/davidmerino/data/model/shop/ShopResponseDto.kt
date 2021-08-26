package com.davidmerino.data.model.shop


import com.google.gson.annotations.SerializedName

data class ShopResponseDto(
    @SerializedName("sites")
    val sites: List<Site>
)