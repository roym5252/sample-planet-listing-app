package com.jpm.feature_planet_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
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
class PlanetListScreenPreDataLoadUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var planetListViewModel: PlanetListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `check loader is displayed`() {

        Mockito.`when`(planetListViewModel.planetPagingFlow).thenReturn(
            flowOf(PagingData.empty())
        )

        setContent()

        composeTestRule.onNodeWithTag("planetListLoader", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun `check title is shown`() {

        Mockito.`when`(planetListViewModel.planetPagingFlow).thenReturn(
            flowOf(PagingData.empty())
        )

        setContent()

        composeTestRule.onNodeWithTag("planetListTitle", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun `check subtitle is shown`() {

        Mockito.`when`(planetListViewModel.planetPagingFlow).thenReturn(
            flowOf(PagingData.empty())
        )

        setContent()

        composeTestRule.onNodeWithTag("planetListSubTitle", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun `check error message with reload icon is shown`() {

        composeTestRule.setContent {

            Mockito.`when`(planetListViewModel.planetPagingFlow).thenReturn(
                flowOf(
                    PagingData.from(
                        data = emptyList(),
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

        composeTestRule.onNodeWithTag("reloadViewHolder", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("errorMessage", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("reloadIcon", useUnmergedTree = true).assertIsDisplayed()
    }

    private fun setContent() {

        composeTestRule.setContent {
            PlanetListScreen(planetListViewModel) { _, _ ->
            }
        }
    }
}