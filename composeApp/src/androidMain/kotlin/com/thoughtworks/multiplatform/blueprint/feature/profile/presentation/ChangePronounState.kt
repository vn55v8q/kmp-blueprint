package com.thoughtworks.multiplatform.blueprint.feature.profile.presentation

data class ChangePronounState(
    val isLoading: Boolean = false,
    val isEnabledChange: Boolean = true,
    val isValidPronoun: Boolean = false,
    val pronoun: String = "",
    val message: String = "",
    val isChangedSuccess: Boolean = false
)