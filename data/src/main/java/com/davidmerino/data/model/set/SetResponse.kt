package com.davidmerino.data.model.set


import com.google.gson.annotations.SerializedName

data class SetResponse(
    @SerializedName("sets")
    val sets: List<Set>
)