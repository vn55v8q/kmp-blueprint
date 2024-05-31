package com.thoughtworks.multiplatform.blueprint.feature.account.presentation

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
        viewModelScope.launch {
            nameUpdateLocalString.invoke()
        }
    }

    fun reset(){
        mutableStateFlow.update {
            it.copy(
                isChangedSuccess = false
            )
        }
    }

    fun processName(name: String) {
        viewModelScope.launch {
            val isInvalidName = isInvalidName.invoke(name)
            if (isInvalidName) {
                mutableStateFlow.update {
                    it.copy(
                        isValidUser = false, message = "El nombre no corresponde"
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

    fun saveName(name: String) {
        mutableStateFlow.update {
            it.copy(
                isLoading = true,
                isChangedSuccess = false
            )
        }
        viewModelScope.launch {
            val isChangeName = changeName.invoke(name)
            if(isChangeName){
                mutableStateFlow.update {
                    it.copy(
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
}

data class ChangeNameState(
    val isLoading: Boolean = false,
    val isEnabledChange: Boolean = true,
    val isValidUser: Boolean = false,
    val name: String = "",
    val message: String = "",
    val isChangedSuccess: Boolean = false
)
