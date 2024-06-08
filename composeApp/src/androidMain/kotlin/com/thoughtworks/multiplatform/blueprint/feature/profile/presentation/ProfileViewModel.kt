package com.thoughtworks.multiplatform.blueprint.feature.profile.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.account.domain.GetAccount
import feature.profile.domain.ChangeDescription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getAccount: GetAccount
) : ViewModel() {
    private val urlAvatar = mutableStateOf("")
    private val mutableStateFlow = MutableStateFlow(ProfileState.default())
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

    fun errorUpdateState(message: String) {
        val newState = mutableStateFlow.value.copy(
            isLoading = false, message = message
        )
        if (!newState.isEqualTo(mutableStateFlow.value)) {
            mutableStateFlow.value = newState
        }
    }

    fun updateName(newName: String) {
        mutableStateFlow.update {
            it.copy(name = newName)
        }
    }

    fun updateUser(newUser: String) {
        mutableStateFlow.update {
            it.copy(user = newUser)
        }
    }

    fun updateDescriprion(newDescription: String) {
        mutableStateFlow.update {
            it.copy(shortDescription = newDescription)
        }
    }

    fun updatePronoun(newPronoun: String) {
        mutableStateFlow.update {
            it.copy(pronoun = newPronoun)
        }
    }
}
