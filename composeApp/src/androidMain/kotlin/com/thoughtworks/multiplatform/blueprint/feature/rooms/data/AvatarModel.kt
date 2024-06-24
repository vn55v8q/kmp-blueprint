package com.thoughtworks.multiplatform.blueprint.feature.rooms.data

import com.google.firebase.Timestamp

data class AvatarModel(
    val id: String = "",
    val active: Boolean = false,
    val userCreatedId: String = "",
    val created: Timestamp = Timestamp.now(),
)
