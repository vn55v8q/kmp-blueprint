package com.thoughtworks.multiplatform.blueprint.feature.profile.presentation

data class ChangeUserState(
    val isLoading: Boolean = false,
    val isEnabledChange: Boolean = true,
    val isValidUser: Boolean = false,
    val name: String = "",
    val message: String = "",
    val isChangedSuccess: Boolean = false
)