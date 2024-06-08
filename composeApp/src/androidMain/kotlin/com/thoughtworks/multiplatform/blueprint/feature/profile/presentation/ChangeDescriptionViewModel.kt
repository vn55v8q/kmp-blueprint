package com.thoughtworks.multiplatform.blueprint.feature.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.profile.domain.ChangeDescription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChangeDescriptionViewModel(
    private val changeUser: ChangeDescription
) : ViewModel() {
    private val mutableStateFlow = MutableStateFlow(ChangeDescriptionState())
    val state = mutableStateFlow.asStateFlow()


    fun saveDescription(description: String) {
        mutableStateFlow.update {
            it.copy(
                isLoading = true,
                isChangedSuccess = false
            )
        }
        viewModelScope.launch {
            val isChangeUser = changeUser.invoke(description)
            if (isChangeUser) {
                mutableStateFlow.update {
                    it.copy(
                        description = description,
                        isLoading = false,
                        isChangedSuccess = true,
                    )
                }
            } else {
                mutableStateFlow.update {
                    it.copy(
                        isLoading = false,
                        message = "No se pudo cambiar la description del usuario, intentalo más tarde",
                        isChangedSuccess = false,
                    )
                }
            }
        }
    }

    fun reset() {
        mutableStateFlow.update {
            ChangeDescriptionState()
        }
    }
}

