package com.psyluckco.anibay.data.network

import com.psyluckco.anibay.data.model.Genre
import com.psyluckco.anibay.data.model.Trailer

data class NetworkDetail(
    val mal_id: Int,
    val title: String,
    val synopsis: String,
    val trailer: Trailer,
    val genres: List<Genre>,
    val episodes: Int,
    val score: Double
)
