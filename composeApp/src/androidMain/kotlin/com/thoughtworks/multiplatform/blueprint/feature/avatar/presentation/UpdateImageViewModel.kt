package com.thoughtworks.multiplatform.blueprint.feature.avatar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.account.domain.ImageReference
import feature.account.domain.TypeImage
import feature.avatar.domain.UploadAvatar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import platform.log.Log

class UpdateImageViewModel(
    private val uploadAvatar: UploadAvatar
) : ViewModel() {

    private val mutableStateFlow = MutableStateFlow(UpdateImageState.default())
    val state = mutableStateFlow.asStateFlow()
    fun uploadImage(rawUri: String, typeImage: TypeImage) {
        val loadingState = mutableStateFlow.value.copy(
            isLoading = true
        )
        if (!loadingState.isEqualTo(mutableStateFlow.value)) {
            mutableStateFlow.value = loadingState
        }

        viewModelScope.launch {
            try {
                val imageReference = ImageReference(rawUri, typeImage)
                val urlReference = uploadAvatar.invoke(imageReference)
                val newState = mutableStateFlow.value.copy(
                    isLoading = false,
                    isSelectedImage = true,
                    urlAvatar = urlReference.url
                )
                if (!newState.isEqualTo(mutableStateFlow.value)) {
                    mutableStateFlow.value = newState
                }
            } catch (e: Exception) {
                Log.d("Profile", "error e: ${e.message.orEmpty()}")
            }
        }
    }
}

data class UpdateImageState(
    val isLoading: Boolean,
    val isSelectedImage: Boolean,
    val urlAvatar: String,
    val name: String,
) {
    companion object {
        fun default() = UpdateImageState(
            true,
            false,
            "",
            "",
        )
    }

    fun isEqualTo(other: UpdateImageState): Boolean {
        return this == other
    }
}