package com.thoughtworks.multiplatform.blueprint.feature.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.profile.domain.ChangeName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import platform.validators.domain.IsInvalidName
import platform.validators.domain.UpdateLocalString

class ChangeNameViewModel(
    private val nameUpdateLocalString: UpdateLocalString,
    private val isInvalidName: IsInvalidName,
    private val changeName: ChangeName
) : ViewModel() {
    private val mutableStateFlow = MutableStateFlow(ChangeNameState())
    val state = mutableStateFlow.asStateFlow()

    init {
        // TODO: Llevar esta logica al Caso de uso
        viewModelScope.launch {
            nameUpdateLocalString.invoke()
        }
    }

    fun processName(newName: String) {
        viewModelScope.launch {
            val isInvalidName = isInvalidName.invoke(newName)
            if (isInvalidName) {
                mutableStateFlow.update {
                    it.copy(
                        name = newName,
                        isValidName = false,
                        message = "El nombre no corresponde"
                    )
                }
            } else {
                mutableStateFlow.update {
                    it.copy(
                        name = newName,
                        isValidName = true,
                        message = ""
                    )
                }
            }

        }
    }

    fun saveName() {
        mutableStateFlow.update {
            it.copy(
                isLoading = true,
                isChangedSuccess = false
            )
        }
        viewModelScope.launch {
            val isChangeName = changeName.invoke(state.value.name)
            if (isChangeName) {
                mutableStateFlow.update {
                    it.copy(
                        name = state.value.name,
                        isLoading = false,
                        isChangedSuccess = true,
                    )
                }
            } else {
                mutableStateFlow.update {
                    it.copy(
                        isLoading = false,
                        message = "No se pudo cambiar el nombre, intentalo más tarde",
                        isChangedSuccess = false,
                    )
                }
            }
        }
    }

    fun reset() {
        mutableStateFlow.update {
            ChangeNameState()
        }
    }
}

