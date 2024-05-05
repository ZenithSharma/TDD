package com.example.myfirstunittest.details

import retrofit2.http.GET
import retrofit2.http.Path

interface PlayListDetailsApi {
    @GET("playlist-details/{id}")
    suspend fun fetchPlaylistDetails(@Path("id") id: String): PlaylistDetails
}
