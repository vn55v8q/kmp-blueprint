package feature.rooms.domain

import feature.avatar.domain.Avatar
import kotlinx.coroutines.flow.Flow

interface RoomClient {
    fun getRoomsFlow(userId: String) : Flow<List<Room>>
    suspend fun create(avatar: Avatar) : Room
    suspend fun join(idRoom: String, avatar: Avatar) : Room
}

