package com.project.domagic.data.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SymbologyManaCostResponseDto(
    @SerializedName("symbol") val symbol: String,
    @SerializedName("svg_uri") val svgUri: String
)
