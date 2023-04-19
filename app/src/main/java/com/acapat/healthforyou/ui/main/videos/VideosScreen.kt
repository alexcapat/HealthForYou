package com.acapat.healthforyou.ui.main.videos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VideosScreen() {
    val youtubeUrl = "https://www.youtube.com/watch?v=WpkX7DQLfVs&t=769s"
    val wearableUrl = "https://www.fitbit.com/global/eu/products"
    val uriHandler = LocalUriHandler.current
    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = { uriHandler.openUri(youtubeUrl) },
            modifier = Modifier.fillMaxWidth().height(60.dp).padding(top = 16.dp),
            shape = RoundedCornerShape(45.dp)
        ) {
            Text(text = "Watch a training video on YouTube", fontSize = 20.sp)
        }
        Button(
            onClick = { uriHandler.openUri(wearableUrl) },
            modifier = Modifier.fillMaxWidth().height(60.dp).padding(top = 16.dp),
            shape = RoundedCornerShape(45.dp)
        ) {
            Text(text = "Check out these wearables", fontSize = 20.sp)
        }
    }
}