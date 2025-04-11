package com.psyluckco.anibay.data.model

import com.google.gson.annotations.SerializedName

data class Anime(
    val title: String,
    val noOfEpisodes: Int,
    val rating: Double,
    val imgUrl: String
)

data class Images(
    val jpg: ImageUrl,
    val webp: ImageUrl
)

data class ImageUrl(
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("small_image_url") val smallImageUrl: String,
    @SerializedName("large_image_url") val largeImageUrl: String
)
