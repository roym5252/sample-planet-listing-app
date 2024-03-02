package com.jpm.feature_planet_list

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun PlanetListScreen() {
    CircularProgressIndicator(modifier = Modifier.testTag("planetListLoader"))
}