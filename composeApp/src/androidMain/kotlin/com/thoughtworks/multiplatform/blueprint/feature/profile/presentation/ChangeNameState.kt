package com.thoughtworks.multiplatform.blueprint.feature.profile.presentation

data class ChangeNameState(
    val isLoading: Boolean = false,
    val isEnabledChange: Boolean = true,
    val isValidName: Boolean = false,
    val name: String = "",
    val message: String = "",
    val isChangedSuccess: Boolean = false
)

