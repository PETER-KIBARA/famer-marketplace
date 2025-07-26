package com.example.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sample.ui.navigation.AppNavHost
import com.example.sample.ui.theme.SmartFarmMarketPlaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartFarmMarketPlaceTheme {
                AppNavHost()
            }
        }
    }
}
