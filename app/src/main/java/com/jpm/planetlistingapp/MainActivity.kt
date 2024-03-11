package com.jpm.planetlistingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jpm.feature_planet_list.PlanetListScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.jpm.feature_planet_detail.PlanetDetailScreen
import com.jpm.util.LogUtil
import com.jpm.util.makeSystemStatusBarTransparent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        makeSystemStatusBarTransparent(R.color.grey)
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
                LogUtil.d("Planet ID: $id and Title:$name")
                navController.navigate("$SCREEN_NAME_PLANET_DETAIL/${id}/${name}")
            }
        }

        composable(
            route = "$SCREEN_NAME_PLANET_DETAIL/{$ARGUMENT_PLANET_ID}/{$ARGUMENT_PLANET_TITLE}",
            arguments = listOf(navArgument(ARGUMENT_PLANET_ID) {
                type = NavType.LongType
            }, navArgument(ARGUMENT_PLANET_TITLE) {
                type = NavType.StringType
            })

        ) {
            val planetId = it.arguments?.getLong(ARGUMENT_PLANET_ID)
            val productTitle = it.arguments?.getString(ARGUMENT_PLANET_TITLE)

            if (planetId != null) {
                PlanetDetailScreen(planetId, productTitle, navController = navController)
            }

        }
    }

}