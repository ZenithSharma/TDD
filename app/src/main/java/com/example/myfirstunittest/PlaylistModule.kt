package com.example.myfirstunittest
import com.example.myfirstunittest.playlist.PlaylistApi
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("OkHttp", client)

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://172.20.10.2:3000/").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }


    @Provides
    fun playlistApi(retrofit: Retrofit): PlaylistApi {
        return retrofit.create(PlaylistApi::class.java)
    }
}