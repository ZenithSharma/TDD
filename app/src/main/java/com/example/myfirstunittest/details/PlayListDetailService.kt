package com.example.myfirstunittest.details

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayListDetailService @Inject constructor(
   private val api: PlayListDetailsApi
) {
    suspend fun fetchPlaylistDetails(id: String): Flow<Result<PlaylistDetails>> {
        return flow {

            emit(Result.success(api.fetchPlaylistDetails(id)))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}
