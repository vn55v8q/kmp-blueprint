package com.thoughtworks.multiplatform.blueprint.feature.account.presentation

data class LoginState(
    val isLoading: Boolean,
    val email: String,
    val password: String,
    val currentStep: Int,
    val isValidEmail: Boolean,
    val isValidPassword: Boolean,
    val errorMessage: String? = null
) {
    companion object {
        fun default() = LoginState(
            isLoading = false,
            email = "",
            password = "",
            currentStep = 0,
            isValidEmail = false,
            isValidPassword = false,
        )
    }
}