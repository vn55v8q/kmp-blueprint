package com.thoughtworks.multiplatform.blueprint.feature.account.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.account.domain.GetAccount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import platform.log.Log

class ProfileViewModel(
    private val getAccount: GetAccount
) : ViewModel() {
    private val urlAvatar = mutableStateOf("")
    private val mutableStateFlow = MutableStateFlow(ProfileState.default())
    private val mutableStepList = mutableListOf(ProfileStep.DEFAULT)
    val state = mutableStateFlow.asStateFlow()

    fun setUrl(url: String){
        urlAvatar.value = url
    }

    fun fetch() {
        try {
            viewModelScope.launch {
                val account = getAccount.invoke()
                val newState = mutableStateFlow.value.copy(
                    isLoading = false,
                    urlImage = urlAvatar.value,
                    name = account.name,
                    user = account.user,
                    pronoun = account.pronoun,
                    shortDescription = account.description,
                    message = ""
                )
                if (!newState.isEqualTo(mutableStateFlow.value)) {
                    mutableStateFlow.value = newState
                }
            }
        } catch (e: Exception) {
            errorUpdateState(e.message.orEmpty())
        }
    }

    fun updateName(name: String) {
        val newState = mutableStateFlow.value.copy(name = name)
        if (!newState.isEqualTo(mutableStateFlow.value)) {
            mutableStateFlow.value = newState
        }
    }

    fun clickChangeImage() {
        mutableStepList.add(ProfileStep.UPLOAD_IMAGE)
        val newState = mutableStateFlow.value.copy(
            currentStep = ProfileStep.UPLOAD_IMAGE
        )
        if (!newState.isEqualTo(mutableStateFlow.value)) {
            mutableStateFlow.value = newState
        }
    }

    fun clickProfile(){
        mutableStepList.add(ProfileStep.EDIT)
        val newState = mutableStateFlow.value.copy(
            currentStep = ProfileStep.EDIT
        )
        if (!newState.isEqualTo(mutableStateFlow.value)) {
            mutableStateFlow.value = newState
        }
    }

    fun previousScreen() {
        mutableStepList.removeLastOrNull()
        if (mutableStepList.isEmpty()) {
            val newState = mutableStateFlow.value.copy(
                isFinishFlow = true
            )
            if (!newState.isEqualTo(mutableStateFlow.value)) {
                mutableStateFlow.value = newState
            }
        } else {
            val previousStep = mutableStepList.last()
            val newState = mutableStateFlow.value.copy(
                currentStep = previousStep
            )
            if (!newState.isEqualTo(mutableStateFlow.value)) {
                mutableStateFlow.value = newState
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

enum class ProfileStep {
    DEFAULT,
    EDIT,
    UPLOAD_IMAGE,
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
    fun isEqualTo(other: ProfileState): Boolean {
        return this == other
    }
}