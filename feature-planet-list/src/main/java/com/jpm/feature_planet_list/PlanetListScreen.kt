package com.jpm.feature_planet_list

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jpm.common_ui.compose.CustomProgressLoader

@Composable
fun PlanetListScreen(planetListViewModel: PlanetListViewModel = hiltViewModel(),) {

    Column {

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

        Text(
            text = stringResource(R.string.planet_list_title),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight(800),
                fontFamily = FontFamily(Font(com.jpm.common_ui.R.font.montserrat_bold)),
                color = Color.Black,

                ),
            modifier = Modifier.testTag("planetListSubTitle")
        )

        CustomProgressLoader(
            modifier = Modifier.testTag("planetListLoader"), contentDescriptionText = stringResource(
                id = R.string.loading
            )
        )
    }
}