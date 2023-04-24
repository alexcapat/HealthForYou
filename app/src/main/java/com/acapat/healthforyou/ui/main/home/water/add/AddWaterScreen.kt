package com.acapat.healthforyou.ui.main.home.water.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.acapat.healthforyou.R

@Composable
fun AddWaterScreen(modifier: Modifier = Modifier, viewModel: AddWaterViewModel = hiltViewModel()) {
  val uiData = viewModel.uiData.collectAsState().value

  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
      modifier = Modifier.padding(horizontal = 16.dp)
    ) {
      Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(
          painter = painterResource(id = R.drawable.drink_512),
          contentDescription = stringResource(id = R.string.water_description)
        )
        Spacer(Modifier.height(16.dp))
      }
      Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Button(
          onClick = { viewModel.removeGlassOfWater(uiData.emptyGlass) },
          modifier = Modifier.width(80.dp).height(60.dp),
          shape = RoundedCornerShape(30.dp),
          enabled = uiData.total > 0
        ) {
          /* Button 1 content */
          Text(text = "-", fontSize = 20.sp)
        }
        Spacer(Modifier.width(16.dp))
        Button(
          onClick = { viewModel.addGlassOfWater(uiData.fullGlass) },
          modifier = Modifier.width(80.dp).height(60.dp),
          shape = RoundedCornerShape(30.dp),
          enabled = uiData.total < 20
        ) {
          /* Button 2 content */
          Text(text = "+", fontSize = 20.sp)
        }
      }
      Spacer(Modifier.height(16.dp))
      Text(
        text =
          "Total: ${uiData.total.coerceIn(0..20)} ${
          if (uiData.total == 1) "glass" else "glasses"
        }",
        fontSize = 25.sp
      )
      Text(text = "(${uiData.totalMillis}ml)", fontSize = 25.sp)

      Button(onClick = viewModel::insertWater, enabled = uiData.isValid && !uiData.isLoading) {
        Text(text = "Add")
      }
    }
  }
}
