/**
 * Created by developer on 10-04-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.anibay.data.model

import com.google.gson.internal.LinkedTreeMap
import com.psyluckco.anibay.data.network.NetworkAnime
import com.psyluckco.anibay.data.network.NetworkDetail

fun NetworkAnime.toAnime() = Anime(
    id = mal_id,
    title = title,
    noOfEpisodes = episodes,
    rating = score,
    imgUrl = images.jpg.imageUrl
)

fun List<NetworkAnime>.toAnime() = map(NetworkAnime::toAnime)

fun NetworkDetail.toAnimeDetail() = AnimeDetail(
    title = title,
    plot = synopsis,
    noOfEpisodes = episodes,
    rating = score,
    trailer = trailer,
    genres = genres.map { genre -> genre.type }
)

fun List<NetworkDetail>.toAnimeDetail() = map(NetworkDetail::toAnimeDetail)