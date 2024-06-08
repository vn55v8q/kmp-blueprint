package com.thoughtworks.multiplatform.blueprint.feature.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.profile.domain.ChangePronoun
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChangePronounViewModel(
    private val changePronoun: ChangePronoun
) : ViewModel() {
    private val mutableStateFlow = MutableStateFlow(ChangePronounState())
    val state = mutableStateFlow.asStateFlow()

    fun savePronoun(pronoun: String) {
        mutableStateFlow.update {
            it.copy(
                isLoading = true,
                isChangedSuccess = false
            )
        }
        viewModelScope.launch {
            val isChangePronoun = changePronoun.invoke(pronoun)
            if (isChangePronoun) {
                mutableStateFlow.update {
                    it.copy(
                        pronoun = pronoun,
                        isLoading = false,
                        isChangedSuccess = true,
                    )
                }
            } else {
                mutableStateFlow.update {
                    it.copy(
                        isLoading = false,
                        message = "No se pudo cambiar el pronombre del usuario, intentalo más tarde",
                        isChangedSuccess = false,
                    )
                }
            }
        }
    }

    fun reset() {
        mutableStateFlow.update {
            ChangePronounState()
        }
    }
}

