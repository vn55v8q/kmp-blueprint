package com.thoughtworks.multiplatform.blueprint.feature.account.presentation

import platform.validators.domain.PasswordStrength

data class LoginState(
    val isLoading: Boolean,
    val email: String,
    val password: String,
    val passwordStrength: PasswordStrength,
    val currentStep: Int,
    val isValidEmail: Boolean,
    val isValidPassword: Boolean,
    val isLoggedUser: Boolean,
    val errorMessage: String? = null
) {
    companion object {
        fun default() = LoginState(
            isLoading = false,
            email = "",
            password = "",
            currentStep = 0,
            isValidEmail = false,
            passwordStrength = PasswordStrength.EMPTY,
            isValidPassword = false,
            isLoggedUser = false
        )
    }
}