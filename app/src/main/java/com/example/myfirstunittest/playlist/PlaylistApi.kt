package com.example.myfirstunittest.playlist

import retrofit2.http.GET

interface PlaylistApi {

    @GET("playlist")
    suspend fun fetchAllPlayList(): List<PlaylistRaw>
}
