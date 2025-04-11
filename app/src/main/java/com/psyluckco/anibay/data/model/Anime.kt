package com.psyluckco.anibay.data.model

import com.google.gson.annotations.SerializedName

data class Anime(
    val id: Int,
    val title: String,
    val noOfEpisodes: Int,
    val rating: Double,
    val imgUrl: String
)

data class AnimeDetail(
    val title: String,
    val plot: String,
    val noOfEpisodes: Int,
    val rating: Double,
    val genres: List<String>,
    val trailer: Trailer?
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

data class Trailer(
    @SerializedName("youtube_id") val youtubeId: String,
    @SerializedName("url") val url: String,
    @SerializedName("embed_url") val embedUrl: String,
    val images: Any
)

data class Genre(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)
