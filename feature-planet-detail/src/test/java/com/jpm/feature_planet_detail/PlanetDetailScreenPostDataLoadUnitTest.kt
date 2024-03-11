package com.jpm.feature_planet_detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.NavHostController
import com.jpm.core.data.model.Planet
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class PlanetDetailScreenPostDataLoadUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var navigationHostController: NavHostController

    @Mock
    private lateinit var planetDetailViewModel: PlanetDetailViewModel

    @Before
    fun setUp() {

        val planet = Planet(
            1,
            "Test_Planet",
            climate = "test_climate",
            diameter = "test_diameter",
            gravity = "test_gravity",
            orbitalPeriod = "test_orbitalPeriod",
            population="test_population",
            rotationPeriod="test_rotationPeriod",
            surfaceWater="test_surfaceWater",
            terrain="test_terrain"
        )
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(planetDetailViewModel.uiState).thenReturn(
            MutableStateFlow(
                PlanetDetailScreenUiState(
                    false,
                    planet
                )
            )
        )

        setContent()
    }


    @Test
    fun `check planet detail list is displayed`() {
        composeTestRule.onNodeWithTag("planetDetailList", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun `check climate label and value are displayed`() {
        composeTestRule.onNodeWithTag("planetDetailList", useUnmergedTree = true).performScrollToIndex(0)
        composeTestRule.onNodeWithText("Climate", true).assertIsDisplayed()
        composeTestRule.onNodeWithText("test_climate", false).assertIsDisplayed()
    }

    @Test
    fun `check diameter label and value are displayed`() {
        composeTestRule.onNodeWithTag("planetDetailList", useUnmergedTree = true).performScrollToIndex(1)
        composeTestRule.onNodeWithText("Diameter", true).assertIsDisplayed()
        composeTestRule.onNodeWithText("test_diameter", true).assertIsDisplayed()
    }

    @Test
    fun `check gravity label and value are displayed`() {
        composeTestRule.onNodeWithTag("planetDetailList", useUnmergedTree = true).performScrollToIndex(2)
        composeTestRule.onNodeWithText("Gravity", true).assertIsDisplayed()
        composeTestRule.onNodeWithText("test_gravity", true).assertIsDisplayed()
    }

    @Test
    fun `check orbital period label and value are displayed`() {
        composeTestRule.onNodeWithTag("planetDetailList", useUnmergedTree = true).performScrollToIndex(3)
        composeTestRule.onNodeWithText("Orbital Period", true).assertIsDisplayed()
        composeTestRule.onNodeWithText("test_orbitalPeriod", true).assertIsDisplayed()
    }

    @Test
    fun `check population label and value are displayed`() {
        composeTestRule.onNodeWithTag("planetDetailList", useUnmergedTree = true).performScrollToIndex(4)
        composeTestRule.onNodeWithText("Population", true).assertIsDisplayed()
        composeTestRule.onNodeWithText("test_population", true).assertIsDisplayed()
    }

    @Test
    fun `check rotation period label and value are displayed`() {
        composeTestRule.onNodeWithTag("planetDetailList", useUnmergedTree = true).performScrollToIndex(5)
        composeTestRule.onNodeWithText("Rotation Period", true).assertIsDisplayed()
        composeTestRule.onNodeWithText("test_rotationPeriod", true).assertIsDisplayed()
    }

    @Test
    fun `check surface water label and value are displayed`() {
        composeTestRule.onNodeWithTag("planetDetailList", useUnmergedTree = true).performScrollToIndex(6)
        composeTestRule.onNodeWithText("Surface Water", true).assertIsDisplayed()
        composeTestRule.onNodeWithText("test_surfaceWater", true).assertIsDisplayed()
    }

    @Test
    fun `check terrain label and value are displayed`() {
        composeTestRule.onNodeWithTag("planetDetailList", useUnmergedTree = true).performScrollToIndex(7)
        composeTestRule.onNodeWithText("Terrain", true).assertIsDisplayed()
        composeTestRule.onNodeWithText("test_terrain", true).assertIsDisplayed()
    }

    private fun setContent() {

        composeTestRule.setContent {
            PlanetDetailScreen(1, "Test_Planet", navigationHostController, planetDetailViewModel)
        }
    }
}