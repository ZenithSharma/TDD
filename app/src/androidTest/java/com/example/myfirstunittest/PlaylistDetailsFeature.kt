package com.example.myfirstunittest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import org.hamcrest.CoreMatchers.allOf
import org.junit.Test

class PlaylistDetailsFeature : BaseUITest() {

    @Test
    fun displayPlaylistNameAndDetails() {
        navigateToPlayListDetailScreen(0)
        Thread.sleep(1000)
        assertDisplayed("Hard Rock Cafe")
        assertDisplayed("Rock your senses with this timeless signature vibe list. \n\n • Poison \n • You shook me all night \n • Zombie \n • Rock'n Me \n • Thunderstruck \n • I Hate Myself for Loving you \n • Crazy \n • Knockin' on Heavens Door")
    }

    @Test
    fun displaysLoaderWhileFetchingPlaylistDetails() {
        navigateToPlayListDetailScreen(0)
        assertDisplayed(R.id.details_loader)
    }

    @Test
    fun hidesLoader() {
        navigateToPlayListDetailScreen(0)
        Thread.sleep(1000)
        assertNotDisplayed(R.id.details_loader)
    }

    @Test
    fun displaysErrorMessageWhenNetworkFails() {
        navigateToPlayListDetailScreen(1)
        Thread.sleep(1000)
        assertDisplayed(R.string.generic_error)
    }

    @Test
    fun hideErrorMessage(){
        navigateToPlayListDetailScreen(2)
        Thread.sleep(3000)
        assertNotExist(R.string.generic_error)
    }

    private fun navigateToPlayListDetailScreen(row: Int) {
        Thread.sleep(6000)
        onView(
            allOf(
                withId(R.id.playlist_image),
                isDescendantOfA(nthChildOf(withId(R.id.playlists_list), row))
            )
        ).perform(click())
    }
}