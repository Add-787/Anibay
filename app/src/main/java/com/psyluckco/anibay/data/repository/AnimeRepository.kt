package com.psyluckco.anibay.data.repository

import com.psyluckco.anibay.data.model.Anime
import com.psyluckco.anibay.data.model.AnimeDetail
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {

    fun getAnimesStream(): Flow<List<Anime>>

    suspend fun getAnime(animeId: Int): AnimeDetail?

}