package app.ynemreuslu.prayertimes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.ynemreuslu.prayertimes.domain.usescase.GpsControllerUseCase
import app.ynemreuslu.prayertimes.domain.usescase.LocationPermissionUseCase
import app.ynemreuslu.prayertimes.domain.usescase.NotificationPermissionUseCase
import app.ynemreuslu.prayertimes.domain.usescase.SkipButtonUseCase
import app.ynemreuslu.prayertimes.ui.navigation.NavRoute
import app.ynemreuslu.prayertimes.ui.navigation.SetupNavGraph
import app.ynemreuslu.prayertimes.ui.theme.PrayerTimesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import app.ynemreuslu.prayertimes.ui.components.BottomNavigationBar
import app.ynemreuslu.prayertimes.ui.components.bottomNavItems


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var locationPermissionUseCase: LocationPermissionUseCase

    @Inject
    lateinit var gspControllerUseCase: GpsControllerUseCase

    @Inject
    lateinit var notificationUseCase: NotificationPermissionUseCase

    @Inject
    lateinit var skipButtonUseCase: SkipButtonUseCase


//
//    @Inject
//    lateinit var locationPermissionStatusUseCase: LocationPermissionStatusUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.Transparent.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(Color.Transparent.toArgb()),
        )
        setContent {
            PrayerTimesTheme {
                MainContent()
            }
        }
    }

    @Composable
    fun MainContent() {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val hideBottomNavRoutes = listOf(
            NavRoute.LOCATION_PERMISSION.route,
            NavRoute.NOTIFICATION_PERMISSION.route,
            NavRoute.HOME.route,
            NavRoute.MAP.route,
            NavRoute.QIBLE.route

        )
        val shouldShowBottomNav = currentRoute !in hideBottomNavRoutes
        Scaffold(
            bottomBar = {
                if (!shouldShowBottomNav) {
                    BottomNavigationBar(
                        navController = navController,
                        items = bottomNavItems,
                        onItemClick = { item ->
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                            }
                        },
                    )
                }
            }
        ) { innerPadding ->
            SetupNavGraph(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                locationPermissionUseCase = locationPermissionUseCase,
                gspControllerUseCase = gspControllerUseCase,
                notificationUseCase = notificationUseCase,
                skipButtonUseCase = skipButtonUseCase
            )
        }

    }
}

