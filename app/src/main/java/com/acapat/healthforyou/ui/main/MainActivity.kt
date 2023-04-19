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
import androidx.compose.ui.Modifier
import com.acapat.healthforyou.MainNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  @Inject lateinit var mainNavigator: MainNavigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

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

      MaterialTheme(colorScheme = colorScheme) { MainScreen(navigator = mainNavigator, modifier = Modifier.fillMaxSize()) }
    }
  }

}
