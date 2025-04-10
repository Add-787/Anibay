/**
 * Created by developer on 10-04-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.anibay.data.network

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.psyluckco.anibay.data.model.Anime
import timber.log.Timber
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(
    private val animeApi: AnimeApi
) : DataSource {

    override suspend fun loadAnimes(): List<NetworkAnime> {

        val response = animeApi.getAnimes()

        if(response.isSuccessful) {
            Timber.d("Response:%s", response.body())

            val animes = response.body()?.get("data")

            if(animes == null)
            {
                return emptyList()
            }

            val type = object: TypeToken<List<NetworkAnime>>() {}.type
            val animeList: List<NetworkAnime> = Gson().fromJson(animes, type)
            return animeList
        } else {
            return emptyList()
        }

    }
}