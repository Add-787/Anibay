/**
 * Created by developer on 10-04-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.anibay.data.network

interface DataSource {
    suspend fun loadAnimes(): List<NetworkAnime>
}