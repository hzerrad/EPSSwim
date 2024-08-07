package com.example.epsswim.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.epsswim.presentation.navigation.AppNavigation
import com.example.epsswim.presentation.ui.common.componants.MyBottomBar
import com.example.epsswim.presentation.ui.theme.EPSSwimTheme
import com.example.epsswim.presentation.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            EPSSwimTheme {
                val navController = rememberNavController()
                val isTrainer = true
                val concernedRoutes = Constants.concernedRoutes
                Scaffold (
                    bottomBar = {
                        if (isTrainer && (navController.currentBackStackEntryAsState().value?.destination?.route in concernedRoutes) ){
                            MyBottomBar(
                                navController
                            )
                        }
                    },
                    contentWindowInsets = WindowInsets(0,0,0,)
                ){
                    AppNavigation(
                        modifier = Modifier.padding(
                            bottom = if (it.calculateBottomPadding() >= 50.dp) it.calculateBottomPadding() - 50.dp else 0.dp),
                        navController=navController,
                        isTrainer = isTrainer
                    )
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EPSSwimTheme {
        Greeting("Android")
    }
}