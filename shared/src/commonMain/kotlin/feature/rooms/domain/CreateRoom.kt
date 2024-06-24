package feature.rooms.domain

import feature.avatar.domain.GetAvatar

class CreateRoom(
    private val getAvatar: GetAvatar,
    private val roomClient: RoomClient
) {
    suspend fun invoke() : Room {
        return try {
            val avatar = getAvatar.invoke()
            roomClient.create(avatar)
        } catch (e: Exception){
            throw CreateRoomException()
        }
    }
}