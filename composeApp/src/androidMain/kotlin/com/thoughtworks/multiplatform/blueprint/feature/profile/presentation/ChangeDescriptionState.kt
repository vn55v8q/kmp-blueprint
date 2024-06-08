package com.thoughtworks.multiplatform.blueprint.feature.profile.presentation

data class ChangeDescriptionState(
    val isLoading: Boolean = false,
    val isEnabledChange: Boolean = true,
    val isValidUser: Boolean = false,
    val description: String = "",
    val message: String = "",
    val isChangedSuccess: Boolean = false
)