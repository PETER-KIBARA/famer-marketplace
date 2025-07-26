package com.example.sample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sample.ui.screens.home.LoginScreen
import com.example.sample.ui.screens.splash.SplashScreen
import com.yourcompany.smartfarmmarketplace.OnboardingScreen

@Composable
fun AppNavHost(){
    val navController = rememberNavController()

    NavHost(
        modifier =Modifier,
        navController = navController,
        startDestination = Destinations.Splash
    ) {
        composable<Destinations.Splash> {
            SplashScreen (
                onSplashFinished = {
                    navController.navigate(Destinations.Home){
                        popUpTo(Destinations.Splash){
                            inclusive =true
                        }
                    }
                }
            )
        }
        composable<Destinations.Home>{
            OnboardingScreen(
                navController=navController
            )
        }


        composable<Destinations.Login> {
            LoginScreen()
        }
    }

}