package com.example.sample.ui.navigation

import kotlinx.serialization.Serializable

sealed class Destinations {
    @Serializable
    object Splash
    @Serializable
    object Home
    @Serializable
    object Second

    @Serializable
    object Login
}