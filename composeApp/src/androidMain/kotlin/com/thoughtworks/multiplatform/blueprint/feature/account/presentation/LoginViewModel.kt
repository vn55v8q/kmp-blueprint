package com.thoughtworks.multiplatform.blueprint.feature.account.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.account.domain.LoginUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import platform.validators.domain.IsInvalidEmailForLogin
import platform.validators.domain.PasswordValidator

class LoginViewModel(
    private val isInvalidEmailForLogin: IsInvalidEmailForLogin,
    private val passwordValidator: PasswordValidator,
    private val loginUser: LoginUser
) : ViewModel() {

    private val mutableStateFlow = MutableStateFlow(LoginState.default())
    val state = mutableStateFlow.asStateFlow()

    fun processEmail(email: String) {
        viewModelScope.launch {

            val isInvalidEmail = isInvalidEmailForLogin.invoke(email)
            if (isInvalidEmail) {
                mutableStateFlow.update {
                    it.copy(
                        email = email,
                        isValidEmail = false,
                        errorMessage = "Ingresa un correo válido",
                        currentStep = 0
                    )
                }
            } else {
                mutableStateFlow.update {
                    it.copy(
                        email = email, isValidEmail = true, errorMessage = null, currentStep = 1
                    )
                }
            }
        }
    }

    fun processPass(pass: String) {
        viewModelScope.launch {
            val passwordStrength = passwordValidator.invoke(pass)
            if (passwordStrength.isValid) {
                mutableStateFlow.update {
                    it.copy(
                        password = pass,
                        isLoading = true,
                        isValidPassword = true,
                        errorMessage = null
                    )
                }
                requestLogin()
            } else {
                mutableStateFlow.update {
                    it.copy(
                        password = pass,
                        isLoading = false,
                        isValidPassword = false,
                        errorMessage = passwordStrength.message
                    )
                }
            }

        }
    }

    fun requestLogin() {
        viewModelScope.launch {
            try {
                if (loginUser.invoke(state.value.email, state.value.password)) {
                    mutableStateFlow.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "User loged de pana",
                        )
                    }
                } else {
                    mutableStateFlow.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "User loged de perro",
                        )
                    }
                }
            } catch (e: Exception) {
                mutableStateFlow.update {
                    it.copy(
                        isLoading = false, errorMessage = e.message.orEmpty(), currentStep = 4
                    )
                }
            }
        }
    }

    fun clearErrorMessage() {
        mutableStateFlow.update {
            it.copy(
                errorMessage = null,
            )
        }
    }

    fun onLastStepProcess() {
        val newStep = state.value.currentStep - 1
        if (newStep >= 0) {
            mutableStateFlow.update {
                it.copy(
                    currentStep = newStep,
                )
            }
        }
    }


}

