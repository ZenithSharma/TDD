package com.example.myfirstunittest.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PlaylistDetailsViewModel(
    private val playListDetailService: PlayListDetailService
) : ViewModel() {
    val loader = MutableLiveData<Boolean>()
    val playlistDetails: MutableLiveData<Result<PlaylistDetails>> = MutableLiveData()

    fun getPlaylistDetails(id: String) {
        viewModelScope.launch {
            loader.postValue(true)
            playListDetailService.fetchPlaylistDetails(id)
                .onEach {
                    loader.postValue(false)
                }
                .collect() {
                playlistDetails.postValue(it)
            }
        }
    }

}
