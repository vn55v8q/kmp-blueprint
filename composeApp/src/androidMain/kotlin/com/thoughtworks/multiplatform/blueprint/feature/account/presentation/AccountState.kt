package com.thoughtworks.multiplatform.blueprint.feature.account.presentation

data class AccountState(
    val isLoading: Boolean,
    val currentStep: Int,
    val isValidName: Boolean,
    val isValidUser: Boolean,
    val isValidEmail: Boolean,
    val isValidPassword: Boolean,
    val isCreateUser: Boolean,
    val errorMessage: String? = null
) {
    companion object {
        fun default() = AccountState(
            isLoading = false,
            currentStep = 0,
            isValidName = false,
            isValidUser = false,
            isValidEmail = false,
            isValidPassword = false,
            isCreateUser = false
        )
    }
}