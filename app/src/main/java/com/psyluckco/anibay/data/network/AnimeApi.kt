package com.psyluckco.anibay.data.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeApi {

    @GET("/top/anime")
    fun getAnimes(): Call<JsonObject>

    @GET("/anime/{id}")
    suspend fun getAnimeById(@Path("id") id: String): Call<JsonObject>

}