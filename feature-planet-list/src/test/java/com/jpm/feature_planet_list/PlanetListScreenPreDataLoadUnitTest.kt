package com.jpm.feature_planet_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class PlanetListScreenPreDataLoadUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `check loader is displayed`() {
        setContent()
        composeTestRule.onNodeWithTag("planetListLoader").assertIsDisplayed()
    }

    private fun setContent() {

        composeTestRule.setContent {
            PlanetListScreen()
        }
    }
}