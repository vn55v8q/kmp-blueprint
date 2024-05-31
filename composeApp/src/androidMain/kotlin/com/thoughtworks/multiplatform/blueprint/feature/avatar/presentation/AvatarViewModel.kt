package com.thoughtworks.multiplatform.blueprint.feature.avatar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.avatar.domain.GetAvatar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AvatarViewModel(
    private val getAvatar: GetAvatar
) : ViewModel() {

    private val mutableStateFlow = MutableStateFlow(AvatarState.default())
    val state = mutableStateFlow.asStateFlow()

    fun fetch() {
        viewModelScope.launch {
            try {

                val avatar = getAvatar.invoke()
                val newState = mutableStateFlow.value.copy(
                    isLoading = false,
                    name = avatar.name,
                    user = avatar.user,
                    urlImage = avatar.urlImage,
                )
                if (newState.isEqualTo(mutableStateFlow.value).not()) {
                    mutableStateFlow.value = newState
                }
            } catch (e: Exception) {
                // TODO : Add Crashlytics
                errorUpdateState(e.message.orEmpty())
            }
        }
    }

    fun errorUpdateState(message: String) {
        val newState = mutableStateFlow.value.copy(
            isLoading = false, message = message
        )
        if (!newState.isEqualTo(mutableStateFlow.value)) {
            mutableStateFlow.value = newState
        }
    }
}

data class AvatarState(
    val isLoading: Boolean,
    val name: String,
    val urlImage: String,
    val user: String,
    val message: String
) {
    companion object {
        fun default() = AvatarState(
            true,
            "",
            "",
            "",
            ""
        )
    }

    fun isEqualTo(other: AvatarState): Boolean {
        return this == other
    }
}