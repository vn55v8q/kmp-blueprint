package com.thoughtworks.multiplatform.blueprint.feature.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.profile.domain.ChangeUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import platform.validators.domain.IsInvalidName
import platform.validators.domain.UpdateLocalString

class ChangeUserViewModel(
    private val nameUpdateLocalString: UpdateLocalString,
    private val isInvalidName: IsInvalidName,
    private val changeUser: ChangeUser
) : ViewModel() {
    private val mutableStateFlow = MutableStateFlow(ChangeUserState())
    val state = mutableStateFlow.asStateFlow()

    init {
        // TODO: Llevar esta logica al Caso de uso
        viewModelScope.launch {
            nameUpdateLocalString.invoke()
        }
    }

    fun processUser(name: String) {
        viewModelScope.launch {
            val isInvalidName = isInvalidName.invoke(name)
            if (isInvalidName) {
                mutableStateFlow.update {
                    it.copy(
                        isValidUser = false, message = "El usuario no corresponde"
                    )
                }
            } else {
                mutableStateFlow.update {
                    it.copy(
                        isValidUser = true, message = ""
                    )
                }
            }

        }
    }

    fun saveUser(user: String) {
        mutableStateFlow.update {
            it.copy(
                isLoading = true,
                isChangedSuccess = false
            )
        }
        viewModelScope.launch {
            val isChangeUser = changeUser.invoke(user)
            if (isChangeUser) {
                mutableStateFlow.update {
                    it.copy(
                        name = user,
                        isLoading = false,
                        isChangedSuccess = true,
                    )
                }
            } else {
                mutableStateFlow.update {
                    it.copy(
                        isLoading = false,
                        message = "No se pudo cambiar el usuario, intentalo más tarde",
                        isChangedSuccess = false,
                    )
                }
            }
        }
    }

    fun reset() {
        mutableStateFlow.update {
            ChangeUserState()
        }
    }
}

