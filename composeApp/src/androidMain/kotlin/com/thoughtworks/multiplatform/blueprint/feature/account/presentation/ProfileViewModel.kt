package com.thoughtworks.multiplatform.blueprint.feature.account.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.account.domain.GetAccount
import feature.account.domain.ImageReference
import feature.account.domain.TypeImage
import feature.account.domain.UploadImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import platform.log.Log

class ProfileViewModel(
    private val getAccount: GetAccount,
    private val uploadImage: UploadImage
) : ViewModel() {
    private val mutableStateFlow = MutableStateFlow(ProfileState.default())
    private val mutableStepList = mutableListOf(ProfileStep.DEFAULT)
    val state = mutableStateFlow.asStateFlow()

    fun fetch() {
        try {
            viewModelScope.launch {
                val account = getAccount.invoke()

                mutableStateFlow.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        urlImage = account.urlAvatar,
                        name = account.name,
                        user = account.user,
                        pronoun = account.pronoun,
                        shortDescription = account.description,
                        message = ""
                    )
                }
            }
        } catch (e: Exception) {
            errorUpdateState(e.message.orEmpty())
        }
    }

    fun onClickChangeImage() {
        mutableStepList.add(ProfileStep.UPLOAD_IMAGE)
        mutableStateFlow.update { currentState ->
            currentState.copy(
                currentStep = ProfileStep.UPLOAD_IMAGE
            )
        }
    }

    fun nextScreen() {
        val currentStep = state.value.currentStep
        when (currentStep) {
            ProfileStep.DEFAULT -> {
                mutableStepList.add(ProfileStep.EDIT)
                mutableStateFlow.update { currentState ->
                    currentState.copy(
                        currentStep = ProfileStep.EDIT
                    )
                }
            }

            ProfileStep.EDIT -> {
                mutableStepList.add(ProfileStep.UPLOAD_IMAGE)
                mutableStateFlow.update { currentState ->
                    currentState.copy(
                        currentStep = ProfileStep.UPLOAD_IMAGE
                    )
                }
            }

            ProfileStep.UPLOAD_IMAGE -> errorUpdateState("No existe próximo step")
        }
    }

    fun previousScreen() {
        mutableStepList.removeLastOrNull()
        if (mutableStepList.isEmpty()) {
            mutableStateFlow.update { currentState ->
                currentState.copy(
                    isFinishFlow = true
                )
            }
        } else {
            val previousStep = mutableStepList.last()
            mutableStateFlow.update { currentState ->
                currentState.copy(
                    currentStep = previousStep
                )
            }
            viewModelScope.launch {
                refreshAvatar()
            }
        }
    }

    private suspend fun refreshAvatar() {
        try {
            val refreshAccount = getAccount.invoke()
            if (refreshAccount.urlAvatar != state.value.urlImage) {
                mutableStateFlow.update { currentState ->
                    currentState.copy(
                        urlImage = refreshAccount.urlAvatar
                    )
                }
            }
        } catch (e: Exception) {
            Log.d("Profile", "error e: ${e.message.orEmpty()}")
        }
    }

    fun errorUpdateState(message: String) {
        mutableStateFlow.update { currentState ->
            currentState.copy(
                isLoading = false, message = message
            )
        }
    }

    fun uploadImage(rawUri: String, typeImage: TypeImage) {
        mutableStateFlow.update { currentState ->
            currentState.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            try {
                val imageReference = ImageReference(rawUri, typeImage)
                val urlReference = uploadImage.invoke(imageReference)
                mutableStateFlow.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        urlImage = urlReference.url
                    )
                }
            } catch (e: Exception) {
                Log.d("Profile", "error e: ${e.message.orEmpty()}")
            }
        }
    }

}

enum class ProfileStep {
    DEFAULT, EDIT, UPLOAD_IMAGE
}

data class ProfileState(
    val isLoading: Boolean,
    val isFinishFlow: Boolean,
    val urlImage: String,
    val name: String,
    val user: String,
    val pronoun: String,
    val shortDescription: String,
    val message: String,
    val currentStep: ProfileStep
) {
    companion object {
        fun default() = ProfileState(
            isLoading = true,
            isFinishFlow = false,
            urlImage = "",
            name = "",
            user = "",
            pronoun = "",
            shortDescription = "",
            message = "",
            currentStep = ProfileStep.DEFAULT
        )
    }
}