package com.jpm.common_ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jpm.common_ui.R

@Composable
fun CustomProgressLoader(progressHolderBoxModifier:Modifier,progressModifier: Modifier, contentDescriptionText: String) {

    Box(modifier = progressHolderBoxModifier
        .semantics {
            contentDescription = contentDescriptionText
        }, contentAlignment = Alignment.Center) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_animation))
        val progress by animateLottieCompositionAsState(
            composition,
            isPlaying = true,
            restartOnPlay = true,
            iterations = LottieConstants.IterateForever
        )

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = progressModifier
        )
    }

}