package com.thoughtworks.multiplatform.blueprint.feature.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.profile.domain.ChangePronoun
import feature.profile.domain.ValidatePronoun
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChangePronounViewModel(
    private val validatePronoun: ValidatePronoun,
    private val changePronoun: ChangePronoun
) : ViewModel() {
    private val mutableStateFlow = MutableStateFlow(ChangePronounState())
    val state = mutableStateFlow.asStateFlow()

    fun changePronoun(pronoun : String){
        viewModelScope.launch {
            mutableStateFlow.update {
                it.copy(
                    isValidPronoun = validatePronoun.invoke(pronoun),
                    pronoun = pronoun
                )
            }
        }
    }

    fun savePronoun() {
        mutableStateFlow.update {
            it.copy(
                isLoading = true,
                isChangedSuccess = false
            )
        }
        viewModelScope.launch {
            val isChangePronoun = changePronoun.invoke(state.value.pronoun)
            if (isChangePronoun) {
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

