package com.project.catalogingmtgcards.data.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SymbologyManaCostResponse(
    @SerializedName("symbol") val symbol: String,
    @SerializedName("svg_uri") val svgUri: String
)
