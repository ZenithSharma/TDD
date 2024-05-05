package com.example.myfirstunittest.playlist

import com.example.myfirstunittest.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.utils.captureValues
import petros.efthymiou.groovy.utils.getValueForTest

class PlaylistViewModelShould : BaseUnitTest() {

    private val repository: PlaylistRepository = mock()
    private val playList = mock<List<Playlist>>()
    private val expected = Result.success(playList)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlaylistsFromRepository() = runTest {
        val viewModel = mockSuccessfulCase()
        viewModel.playlist.getValueForTest()
        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitPlaylistFromRepository() = runTest {
        val viewModel = mockSuccessfulCase()
        assertEquals(expected, viewModel.playlist.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError() {
        val viewModel = mockErrorCase()
        assertEquals(exception, viewModel.playlist.getValueForTest()!!.exceptionOrNull())
    }



    @Test
    fun showSpinnerWhileLoading()  = runTest {
        val viewModel = mockSuccessfulCase()
        viewModel.loader.captureValues{
             viewModel.playlist.getValueForTest()
            assertEquals(true,values[0])
        }
    }

    @Test
    fun closeSpinnerAfterPlaylistLoaded() = runTest{
        val viewModel = mockSuccessfulCase()
        viewModel.loader.captureValues {
            viewModel.playlist.getValueForTest()
            assertEquals(false,values.last())
        }
    }

    @Test
    fun closeSpinnerAfterError() =  runTest{
        val viewModel  = mockErrorCase()
        viewModel.loader.captureValues {
            viewModel.playlist.getValueForTest()
            assertEquals(false,values.last())
        }
    }

    private fun mockSuccessfulCase(): PlaylistViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(flow {
                emit(expected)
            })
        }
        return PlaylistViewModel(repository)
    }

    private fun mockErrorCase(): PlaylistViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(flow {
                emit(Result.failure(exception))
            })
        }
        return PlaylistViewModel(repository)
    }
}