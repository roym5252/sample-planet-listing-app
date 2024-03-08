package com.jpm.feature_planet_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.jpm.common_ui.compose.CustomProgressLoader
import com.jpm.common_ui.compose.InfoMessageAndReload
import com.jpm.common_ui.theme.CustomColor
import com.jpm.core.data.model.Planet


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetListScreen(
    planetListViewModel: PlanetListViewModel = hiltViewModel(),
    onPlanetClick: (planetId: Long, planetTitle: String) -> Unit
) {

    val planets = planetListViewModel.planetPagingFlow.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .background(color = CustomColor.Light_Grey)
            .fillMaxSize()
    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        TitleAndSubTitle(planets)
                    }, colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = CustomColor.Light_Grey,
                    ), modifier = Modifier.testTag("planetListToolbar")
                )
            },
        ) { innerPadding ->

            Box(
                Modifier
                    .padding(top = 8.dp)
                    .background(CustomColor.Light_Grey)
                    .fillMaxSize()
            ) {

                Box(modifier = Modifier.padding(innerPadding)) {

                    when (planets.loadState.refresh) {

                        is LoadState.Loading -> {

                            CustomProgressLoader(
                                Modifier.fillMaxSize(),
                                Modifier
                                    .testTag("planetListLoader")
                                    .size(200.dp), stringResource(
                                    id = R.string.loading
                                )
                            )

                        }

                        is LoadState.Error -> {

                            if (planets.itemCount == 0) {

                                InfoMessageAndReload(stringResource(id = R.string.no_planets_available)) {
                                    planets.refresh()
                                }

                            } else {
                                PlanetList(planets, onPlanetClick)
                            }

                        }

                        else -> {

                            if (planets.itemCount == 0) {

                                CustomProgressLoader(
                                    Modifier.fillMaxSize(),
                                    Modifier
                                        .testTag("planetListLoader")
                                        .size(200.dp), stringResource(
                                        id = R.string.loading
                                    )
                                )

                            } else {
                                PlanetList(planets, onPlanetClick)
                            }

                        }
                    }
                }

            }

        }

    }
}

@Composable
private fun PlanetList(
    planets: LazyPagingItems<Planet>,
    onPlanetClick: (planetId: Long, planetTitle: String) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp)
            .testTag("planetList"),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(
            count = planets.itemCount,
            key = planets.itemKey { it.id },
            contentType = planets.itemContentType { "MyPagingItems" }
        ) { index ->
            val item = planets[index]

            item?.let {
                PlanetListItem(planetName = it.name, Modifier.clickable {
                    onPlanetClick(it.id, it.name)
                })
            }

        }

        item {
            if (planets.loadState.append is LoadState.Loading) {

                CustomProgressLoader(
                    Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(top = 5.dp, bottom = 5.dp),
                    Modifier
                        .testTag("paginationLoader")
                        .size(100.dp), stringResource(
                        id = R.string.loading
                    )
                )
            }
        }

        item {
            if (planets.itemCount > 0 && (planets.loadState.refresh is LoadState.Error)) {

                Box(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            planets.retry()
                        }
                ) {

                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.pagination_load_error_message),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp, bottom = 20.dp)
                            .testTag("paginationLoadErrorMessage"),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(com.jpm.common_ui.R.font.montserrat_bold)),
                            fontWeight = FontWeight(400),
                            color = Color.Black,

                            )
                    )
                }
            }
        }
    }
}

@Composable
private fun TitleAndSubTitle(planets: LazyPagingItems<Planet>) {

    Column(modifier = Modifier.semantics(mergeDescendants = true) {}) {

        Text(
            text = stringResource(R.string.planet_list_title),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight(800),
                fontFamily = FontFamily(Font(com.jpm.common_ui.R.font.montserrat_bold)),
                color = Color.Black,

                ),
            modifier = Modifier.testTag("planetListTitle")
        )

        val subTitle = if (planets.loadState.refresh is LoadState.Loading) {
            stringResource(R.string.loading)
        } else if (planets.itemCount == 0) {
            stringResource(R.string.subtitle_error_text)
        } else {
            stringResource(R.string.planet_data_loaded)
        }

        Text(
            text = subTitle, maxLines = 1, overflow = TextOverflow.Ellipsis, style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(com.jpm.common_ui.R.font.montserrat_light)),
                fontWeight = FontWeight(400),
                color = Color.Black,

                ), modifier = Modifier.testTag("planetListSubTitle")
        )
    }
}