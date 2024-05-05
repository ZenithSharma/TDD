package com.example.myfirstunittest.details

import com.example.myfirstunittest.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PlaylistDetailsServiceShould : BaseUnitTest() {
    private lateinit var service: PlayListDetailService
    private val id = "1"
    private val api: PlayListDetailsApi = mock()
    private val playlistDetails: PlaylistDetails = mock()
    private val exception = RuntimeException("Damn again backend developer 500 !!!")

    @Test
    fun fetchPlaylistDetailsFromApi() = runTest {
        mockSuccessfulCase()
        service.fetchPlaylistDetails(id).single()
        verify(api, times(1)).fetchPlaylistDetails(id)
    }

    @Test
    fun convertValuesToFlowResultAndEmitThem() = runTest {
        mockSuccessfulCase()
        assertEquals(Result.success(playlistDetails), service.fetchPlaylistDetails(id).single())
    }

    @Test
    fun emitErrorResultWhenNetworkFails() = runTest {
        mockErrorCase()
        assertEquals(
            "Something went wrong",
            service.fetchPlaylistDetails(id).single().exceptionOrNull()?.message
        )
    }

    private suspend fun mockErrorCase() {
        whenever(api.fetchPlaylistDetails(id)).thenThrow(exception)
        service = PlayListDetailService(api)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.fetchPlaylistDetails(id)).thenReturn(playlistDetails)
        service = PlayListDetailService(api)
    }
}