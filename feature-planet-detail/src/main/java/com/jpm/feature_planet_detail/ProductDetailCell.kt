package com.jpm.feature_planet_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jpm.common_ui.theme.CustomColor

@Composable
fun ProductDetailCell(
    label: String, value: String?
) {
    Box(modifier = Modifier.background(CustomColor.Light_Grey)) {

        Box(
            modifier = Modifier.padding(
                start = 8.dp,
                end = 16.dp
            )
        ) {

            Column(
                Modifier
                    .padding(start = 8.dp)
                    .background(Color.White)
                    .semantics(mergeDescendants = true) {}) {

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 16.dp,
                            bottom = 16.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = label,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp, end = 4.dp)
                            .testTag("FeatureLabel-${label}"),
                        fontWeight = FontWeight(600),
                        fontFamily = FontFamily(Font(com.jpm.common_ui.R.font.montserrat_medium))
                    )
                    Text(
                        text = if (value.isNullOrEmpty()) "-" else value,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp).testTag("FeatureValue-${label}"),
                        fontWeight = FontWeight(600),
                        fontFamily = FontFamily(Font(com.jpm.common_ui.R.font.montserrat_light))
                    )
                }

                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .padding(start = 8.dp, end = 8.dp)
                        .background(CustomColor.Light_Grey)
                        .fillMaxWidth()
                )
            }
        }
    }
}