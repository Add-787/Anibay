package com.psyluckco.anibay.data.network

import com.psyluckco.anibay.data.model.Images

data class NetworkAnime(
    val mal_id: Int,
    val title: String,
    val score: Double,
    val episodes: Int,
    val images: Images
)
