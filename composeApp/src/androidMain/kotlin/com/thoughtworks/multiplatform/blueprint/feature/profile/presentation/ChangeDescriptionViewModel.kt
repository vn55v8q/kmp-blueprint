package com.thoughtworks.multiplatform.blueprint.feature.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.profile.domain.ChangeDescription
import feature.profile.domain.ValidateDescription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChangeDescriptionViewModel(
    private val validateDescription: ValidateDescription,
    private val changeDescription: ChangeDescription
) : ViewModel() {
    private val mutableStateFlow = MutableStateFlow(ChangeDescriptionState())
    val state = mutableStateFlow.asStateFlow()

    fun changeDescription(newDescription : String){
        viewModelScope.launch {
            mutableStateFlow.update {
              it.copy(
                  isValidDescription = validateDescription.invoke(newDescription),
                  description = newDescription
              )
            }
        }
    }

    fun saveDescription() {
        mutableStateFlow.update {
            it.copy(
                isLoading = true,
                isChangedSuccess = false
            )
        }
        viewModelScope.launch {
            val isChangeUser = changeDescription.invoke(state.value.description)
            if (isChangeUser) {
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

