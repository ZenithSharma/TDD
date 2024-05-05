package com.example.myfirstunittest.playlist

import com.example.myfirstunittest.utils.BaseUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test
import com.example.myfirstunittest.R

class PlaylistMapperShould : BaseUnitTest() {

    private val playlistRaw = PlaylistRaw("1", "da name", "da category")
    private val playlistRawRock = PlaylistRaw("1", "da name", "rock")

    private val mapper = PlayListMapper()
    private val playlists = mapper(listOf(playlistRaw))
    private val playlistRock = mapper(listOf(playlistRawRock))[0]
    private val playlist = playlists[0]

    @Test
    fun keepSameId() {
        assertEquals(playlistRaw.id, playlist.id)
    }

    @Test
    fun keepSameName(){
        assertEquals(playlistRaw.name,playlist.name)
    }

    @Test
    fun keepSameCategory(){
        assertEquals(playlistRaw.category,playlist.category)
    }

    @Test
    fun mapDefaultImageWhenNotRock(){
        assertEquals(R.mipmap.playlist,playlist.image)
    }
    @Test
    fun mapRockImageWhenRockCategory(){
        assertEquals(R.mipmap.rock,playlistRock.image)
    }
}