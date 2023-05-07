package com.acapat.healthforyou.ui.main.home.steps.data

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class StepsDataViewModel @Inject constructor(
    @ApplicationContext private val context: Context
): ViewModel(){


//    val healthPermissionManager = HealthPermissionManager(mStore)
//    val stepCountPermissionKeySet = setOf(HealthPermissionManager.PermissionType.READ)
//    val result = healthPermissionManager.requestPermissions(stepCountPermissionKeySet)


}

