package feature.rooms.domain

import feature.avatar.domain.Avatar

data class AvatarRooms(
    val avatar: Avatar,
    val rooms: List<Room>
)
