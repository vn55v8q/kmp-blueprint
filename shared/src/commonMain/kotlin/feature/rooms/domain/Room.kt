package feature.rooms.domain

import feature.avatar.domain.Avatar

data class Room(
    val id: String,
    val creatorId: String,
    val isJoinedRoom : Boolean,
    val users: List<Avatar>
)