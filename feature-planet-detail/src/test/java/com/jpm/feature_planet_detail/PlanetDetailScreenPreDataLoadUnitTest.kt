package com.jpm.feature_planet_detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class PlanetDetailScreenPreDataLoadUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var navigationHostController: NavHostController

    @Mock
    private lateinit var planetDetailViewModel: PlanetDetailViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `check loader is displayed`() {
        Mockito.`when`(planetDetailViewModel.uiState).thenReturn(
            MutableStateFlow(
                PlanetDetailScreenUiState(
                    true, null
                )
            )
        )
        setContent()
        composeTestRule.onNodeWithTag("planeDetailLoader", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun `check toolbar is displayed`() {
        Mockito.`when`(planetDetailViewModel.uiState).thenReturn(
            MutableStateFlow(
                PlanetDetailScreenUiState(
                    true, null
                )
            )
        )
        setContent()
        composeTestRule.onNodeWithTag("planetDetailToolbar", useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag("planetDetailToolbar", useUnmergedTree = true).onChild()
            .assertTextEquals("Test_Planet")
    }

    @Test
    fun `check toolbar back button is displayed`() {
        Mockito.`when`(planetDetailViewModel.uiState).thenReturn(
            MutableStateFlow(
                PlanetDetailScreenUiState(
                    true, null
                )
            )
        )
        setContent()
        composeTestRule.onNodeWithTag("planetDetailBackButton", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    private fun setContent() {

        composeTestRule.setContent {
            PlanetDetailScreen(1, "Test_Planet", navigationHostController, planetDetailViewModel)
        }
    }
}