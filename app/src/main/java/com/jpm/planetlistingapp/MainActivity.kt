package com.jpm.planetlistingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jpm.feature_planet_list.PlanetListScreen
import com.jpm.feature_planet_list.SCREEN_NAME_PLANET_LIST
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jpm.util.LogUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SCREEN_NAME_PLANET_LIST) {

        composable(SCREEN_NAME_PLANET_LIST) {

            PlanetListScreen { id, name ->
                LogUtil.d("Clicked planet ID:$id and planet name:$name")
            }
        }
    }

}