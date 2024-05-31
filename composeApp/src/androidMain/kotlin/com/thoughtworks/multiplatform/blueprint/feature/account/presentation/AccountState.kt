package com.thoughtworks.multiplatform.blueprint.feature.account.presentation

import platform.validators.domain.PasswordStrength

data class AccountState(
    val isLoading: Boolean,
    val currentStep: Int,
    val isValidName: Boolean,
    val isValidUser: Boolean,
    val isValidEmail: Boolean,
    val isValidPassword: Boolean,
    val passwordStrength: PasswordStrength,
    val isCreateUser: Boolean,
    val isLoggedUser: Boolean,
    val message: String? = null
) {
    companion object {
        fun default() = AccountState(
            isLoading = false,
            currentStep = 0,
            isValidName = true,
            isValidUser = false,
            isValidEmail = false,
            passwordStrength = PasswordStrength.EMPTY,
            isValidPassword = false,
            isCreateUser = false,
            isLoggedUser = false,
        )
    }
}