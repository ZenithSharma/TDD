package com.example.myfirstunittest.playlist

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaylistService @Inject constructor(private val playlistApi: PlaylistApi) {
    suspend fun fetchPlayList(): Flow<Result<List<PlaylistRaw>>> {
        return flow {
            emit(Result.success(playlistApi.fetchAllPlayList()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}
