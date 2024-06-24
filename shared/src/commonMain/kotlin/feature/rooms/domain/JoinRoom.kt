package feature.rooms.domain

import feature.avatar.domain.GetAvatar
import platform.log.Log

class JoinRoom (
    private val getAvatar: GetAvatar,
    private val roomClient: RoomClient
) {
    suspend fun invoke(idRoom: String) : Room {
        return try {
            val avatar = getAvatar.invoke()
            roomClient.join(idRoom, avatar)
        } catch (e: Exception){
            Log.d("Rooms", "Error al crear la sala: ${e.message}")
            throw CreateRoomException()
        }
    }
}