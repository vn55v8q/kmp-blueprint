package com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import platform.log.Log
import platform.theme.domain.GetTheme
import platform.theme.domain.SaveTheme
import platform.theme.domain.ThemeSelected
import platform.theme.domain.ThemeType

class ThemeViewModel(
    private val getTheme: GetTheme,
    private val saveTheme: SaveTheme
) : ViewModel() {

    private val mutableState = MutableStateFlow(ThemeState())
    val state = mutableState.asStateFlow()

    init {
        fetch()
    }

    fun fetch() {
        viewModelScope.launch {
            val themeSelected = getTheme.invoke()
            Log.d("ThemeViewModel", "themeSelected: $themeSelected")
            mutableState.update { newState ->
                newState.copy(theme = themeSelected)
            }
        }
    }

    fun save(type: ThemeType, isDark: Boolean) {
        Log.d("ThemeViewModel", "type: $type, isDark: $isDark")
        viewModelScope.launch {
            saveTheme.invoke(type, isDark)
            fetch()
        }
    }

}

data class ThemeState(
    val theme: ThemeSelected = ThemeSelected()
)