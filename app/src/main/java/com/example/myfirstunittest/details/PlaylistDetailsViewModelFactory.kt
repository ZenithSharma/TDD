package com.example.myfirstunittest.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class PlaylistDetailsViewModelFactory @Inject constructor(private val playListDetailService: PlayListDetailService) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlaylistDetailsViewModel(playListDetailService) as T
    }
}
