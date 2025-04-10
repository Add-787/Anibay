/**
 * Created by developer on 10-04-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.anibay.data.model

import com.psyluckco.anibay.data.network.NetworkAnime

fun NetworkAnime.toAnime() = Anime(
    title = title
)

fun List<NetworkAnime>.toAnime() = map(NetworkAnime::toAnime)