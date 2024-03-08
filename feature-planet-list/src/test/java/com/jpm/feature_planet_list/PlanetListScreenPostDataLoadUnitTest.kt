package com.jpm.feature_planet_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import com.jpm.core.data.model.Planet
import kotlinx.coroutines.flow.flowOf
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
class PlanetListScreenPostDataLoadUnitTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var planetListViewModel: PlanetListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `check planet list is shown`() {
        setContentWithPositiveState()
        composeTestRule.onNodeWithTag("planetList", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("planetListItem", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun `check planet list bottom loader is shown`() {
        setContentWithPositiveState()
        composeTestRule.onNodeWithTag("paginationLoader", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun `check error message is shown`() {
        setContentWithNegativeState()
        composeTestRule.onNodeWithTag("paginationLoadErrorMessage", useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Unable to load data. Tap to retry.", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    private fun setContentWithPositiveState() {

        composeTestRule.setContent {

            Mockito.`when`(planetListViewModel.planetPagingFlow).thenReturn(
                flowOf(
                    PagingData.from(
                        data = listOf(Planet(1, "test_planet")),
                        sourceLoadStates = LoadStates(
                            LoadState.NotLoading(true),
                            prepend = LoadState.NotLoading(true),
                            append = LoadState.Loading
                        )
                    )
                )
            )

            PlanetListScreen(planetListViewModel) { _, _ ->
            }
        }
    }

    private fun setContentWithNegativeState() {

        composeTestRule.setContent {

            Mockito.`when`(planetListViewModel.planetPagingFlow).thenReturn(
                flowOf(
                    PagingData.from(
                        data = listOf(Planet(1, "test_planet")),
                        sourceLoadStates = LoadStates(
                            refresh = LoadState.Error(Exception()),
                            prepend = LoadState.NotLoading(true),
                            append = LoadState.Error(Exception())
                        )
                    )
                )
            )

            PlanetListScreen(planetListViewModel) { _, _ ->
            }
        }
    }
}