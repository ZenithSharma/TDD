package com.example.myfirstunittest.playlist

import com.example.myfirstunittest.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PlaylistServiceShould : BaseUnitTest() {
    private lateinit var service: PlaylistService
    private val playListApi: PlaylistApi = mock()
    private val playlists: List<PlaylistRaw> = mock()

    @Test
    fun getPlayListFromApi() = runTest {
        service = PlaylistService(playListApi)
        service.fetchPlayList().first()
        verify(playListApi, times(1)).fetchAllPlayList()
    }

    @Test
    fun convertValuesToFlowResultAndEmitsThem() = runTest {
        mockSuccessfulCase()
        assertEquals(Result.success(playlists), service.fetchPlayList().first())
    }

    @Test
    fun emitsErrorResultWhenNetworkFails() = runTest {
        mockFailureCase()
        assertEquals(
            "Something went wrong", service.fetchPlayList().first().exceptionOrNull()?.message
        )
    }

    private suspend fun mockSuccessfulCase() {
        whenever(playListApi.fetchAllPlayList()).thenReturn(playlists)
        service = PlaylistService(playListApi)
    }

    private suspend fun mockFailureCase() {
        whenever(playListApi.fetchAllPlayList()).thenThrow(RuntimeException("Damn backend developer"))
        service = PlaylistService(playListApi)
    }
}