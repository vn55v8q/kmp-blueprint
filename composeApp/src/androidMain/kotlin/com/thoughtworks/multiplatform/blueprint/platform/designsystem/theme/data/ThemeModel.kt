package com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.data

import com.google.firebase.Timestamp

data class ThemeModel(
    val type: String,
    val dark: Boolean,
    val update: Timestamp = Timestamp.now()
)
