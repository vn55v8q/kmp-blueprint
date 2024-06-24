package com.thoughtworks.multiplatform.blueprint.feature.rooms.data

import com.google.firebase.Timestamp
import feature.avatar.domain.Avatar

data class RoomModel(
    val id: String = "",
    val active: Boolean = false,
    val userCreatedId: String = "",
    val created: Timestamp = Timestamp.now(),
    val users: List<Avatar> = emptyList()
)
