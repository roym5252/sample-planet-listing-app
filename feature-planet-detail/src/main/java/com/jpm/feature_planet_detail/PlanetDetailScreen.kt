package com.jpm.feature_planet_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jpm.common_ui.compose.CustomProgressLoader
import com.jpm.common_ui.theme.CustomColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetDetailScreen(
    planetId: Long,
    planetTitle: String?,
    navController: NavHostController,
    planetDetailViewModel: PlanetDetailViewModel = hiltViewModel()
) {

    val planetDetailUiState by planetDetailViewModel.uiState.collectAsState()

    DisposableEffect(key1 = planetDetailViewModel) {
        planetDetailViewModel.getPlanetDetail(planetId)
        onDispose {

        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                    TopAppBar(

                        title = {
                            planetTitle?.let {
                                Text(text = planetTitle)
                            }

                        }, colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = CustomColor.Light_Grey,
                        ), modifier = Modifier.testTag("planetDetailToolbar")
                    )

                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CustomColor.Light_Grey,
                ), modifier = Modifier.testTag("planetListToolbar"),

                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.testTag("planetDetailBackButton")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_button)
                        )
                    }
                }
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

                if (planetDetailUiState.loading){

                    CustomProgressLoader(
                        Modifier.fillMaxSize(),
                        Modifier
                            .testTag("planeDetailLoader")
                            .size(200.dp), stringResource(
                            id = R.string.loading_planet_detail
                        )
                    )

                }else{

                    planetDetailUiState.planet?.let {

                        LazyColumn(content = {

                            item{
                                ProductDetailCell(stringResource(id = R.string.climate),it.climate)
                            }

                            item{
                                ProductDetailCell(stringResource(id = R.string.diameter),it.diameter)
                            }

                            item{
                                ProductDetailCell(stringResource(id = R.string.gravity),it.gravity)
                            }

                            item{
                                ProductDetailCell(stringResource(id = R.string.orbital_period),it.orbitalPeriod)
                            }

                            item{
                                ProductDetailCell(stringResource(id = R.string.population),it.population)
                            }

                            item{
                                ProductDetailCell(stringResource(id = R.string.rotation_period),it.rotationPeriod)
                            }

                            item{
                                ProductDetailCell(stringResource(id = R.string.surface_water),it.surfaceWater)
                            }

                            item{
                                ProductDetailCell(stringResource(id = R.string.terrain),it.terrain)
                            }


                        }, modifier = Modifier.fillMaxSize().testTag("planetDetailList"))
                    }
                }

            }
        }
    }

}