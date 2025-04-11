/**
 * Created by developer on 10-04-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.anibay.data.model

import com.google.gson.internal.LinkedTreeMap
import com.psyluckco.anibay.data.network.NetworkAnime

fun NetworkAnime.toAnime() = Anime(
    title = title,
    noOfEpisodes = episodes,
    rating = score,
    imgUrl = images.jpg.imageUrl
)

fun List<NetworkAnime>.toAnime() = map(NetworkAnime::toAnime)