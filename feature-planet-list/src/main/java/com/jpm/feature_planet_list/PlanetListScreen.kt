package com.jpm.feature_planet_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.jpm.common_ui.compose.CustomProgressLoader

@Composable
fun PlanetListScreen() {

    CustomProgressLoader(
        modifier = Modifier.testTag("planetListLoader"), contentDescriptionText = stringResource(
            id = R.string.loading
        )
    )

}