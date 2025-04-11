/**
 * Created by developer on 10-04-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.anibay.data.repository

import com.psyluckco.anibay.data.model.Anime
import com.psyluckco.anibay.data.model.AnimeDetail
import com.psyluckco.anibay.data.model.toAnime
import com.psyluckco.anibay.data.model.toAnimeDetail
import com.psyluckco.anibay.data.network.DataSource
import com.psyluckco.anibay.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkAnimeRepository @Inject constructor(
    private val dataSource: DataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : AnimeRepository {
    override fun getAnimesStream(): Flow<List<Anime>> = flow {
        val animes = dataSource.loadAnimes().toAnime()
        Timber.i("First anime:%s", animes[0])
        emit(animes)
    }

    override suspend fun getAnime(animeId: Int): AnimeDetail? {
        try {

            val anime = dataSource.loadAnimeById(animeId).toAnimeDetail()
            Timber.i("Anime detail:%s", anime.title)
            return anime

        } catch(e: Exception) {
            return null;
        }
    }
}