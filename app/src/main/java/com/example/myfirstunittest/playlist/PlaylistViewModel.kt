package com.example.myfirstunittest.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.onEach

class PlaylistViewModel(private val playlistRepository: PlaylistRepository) : ViewModel() {

    val loader = MutableLiveData<Boolean>()
    val playlist = liveData {
        loader.postValue(true)
        emitSource(playlistRepository.getPlaylists()
            .onEach {
                loader.postValue(false)
            }
            .asLiveData())
    }
}
