package com.project.domagic.data.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ImageUri (@SerializedName("art_crop") val artCrop: String)