package com.acapat.healthforyou.ui.main.home.sleep.add

data class AddSleepUiData(
    val sleep : String ="",
    val isLoading: Boolean = false,
) {
    val isSleepValid = sleep.toIntOrNull() != null
    val isValid = isSleepValid

    val total: Int?
        get() {
            return sleep.toIntOrNull()
        }
}