package com.example.myfirstunittest

import com.example.myfirstunittest.details.PlayListDetailsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
class PlaylistDetailsModule {

    @Provides
    fun playListDetailsApi(retrofit: Retrofit): PlayListDetailsApi {
        return retrofit.create(PlayListDetailsApi::class.java)
    }
}