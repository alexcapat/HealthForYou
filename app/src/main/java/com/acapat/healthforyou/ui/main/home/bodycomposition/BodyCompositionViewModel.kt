package com.acapat.healthforyou.ui.main.home.bodycomposition

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class BodyCompositionViewModel @Inject constructor() : ViewModel(){
    private val _uiData = MutableStateFlow(BodyCompositionUiData())
    val uiData = _uiData.asStateFlow()

//    fun setAge(age: Int) {
//        _uiData.update { it.copy(age = age) }
//    }
    fun setWeight(weight: String) {
        _uiData.update { it.copy(weight = weight) }
    }
    fun setHeight(height: String) {
        _uiData.update { it.copy(height = height) }
    }
}