package com.psyluckco.anibay.data.network

import retrofit2.Call
import retrofit2.http.GET

interface AnimeApi {

    @GET("anime")
    fun getAnime(): Call<List<Any>>
}