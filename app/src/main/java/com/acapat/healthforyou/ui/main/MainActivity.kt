package com.acapat.healthforyou.ui.main

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.StepsRecord
import com.acapat.healthforyou.MainNavigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// build a set of permissions for required data types
// Each permission value is a string data type
val PERMISSIONS =
  setOf(
    HealthPermission.getReadPermission(StepsRecord::class),
    HealthPermission.getWritePermission(StepsRecord::class)
  )

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  @Inject lateinit var mainNavigator: MainNavigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)

    setContent {
      val isDarkMode = isSystemInDarkTheme()
      val colorScheme =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
          if (isDarkMode) {
            dynamicDarkColorScheme(this)
          } else {
            dynamicLightColorScheme(this)
          }
        } else {
          if (isDarkMode) {
            darkColorScheme()
          } else {
            lightColorScheme()
          }
        }

      val systemUiController = rememberSystemUiController()
      val useDarkIcons = !isDarkMode

      DisposableEffect(systemUiController, useDarkIcons) {
        // Update all of the system bar colors to be transparent, and use
        // dark icons if we're in light theme
        systemUiController.setSystemBarsColor(color = Color.Transparent, darkIcons = useDarkIcons)
        onDispose {}
      }
      MaterialTheme(colorScheme = colorScheme) {
        MainScreen(navigator = mainNavigator, modifier = Modifier.fillMaxSize())
      }
    }
  }
}
