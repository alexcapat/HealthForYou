package com.acapat.healthforyou

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(
    val route : String,
    val icon : ImageVector,
    val label : String
) {
    HOME("home", Icons.Default.Home, label = "Home"),
    VIDEOS("videos", Icons.Default.PlayArrow, label = "Videos"),
    MY_STATS("stats", Icons.Default.Star, label= "Stats");
}