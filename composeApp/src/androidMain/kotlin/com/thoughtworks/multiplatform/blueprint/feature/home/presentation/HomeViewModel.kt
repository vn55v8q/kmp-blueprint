package com.thoughtworks.multiplatform.blueprint.feature.home.presentation

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.avatar.domain.GetAvatar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAvatar: GetAvatar
) : ViewModel() {
    private val mutableStateFlow = MutableStateFlow(HomeState.default())
    val state = mutableStateFlow.asStateFlow()

    init {
        fetch()
    }

    fun fetch() {
        viewModelScope.launch {
            try {
                val avatar = getAvatar.invoke()
                mutableStateFlow.update {
                    it.copy(
                        isLoading = false,
                        avatarUrl = avatar.urlImage
                    )
                }
            } catch (e: Exception) {
                // TODO : send event to Crashlytics
            }
        }
    }
}

@Stable
data class HomeState(
    val isLoading: Boolean,
    val avatarUrl: String,
) {
    companion object {
        fun default() = HomeState(true, "")
    }
}