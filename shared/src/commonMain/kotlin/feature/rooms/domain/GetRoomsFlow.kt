package feature.rooms.domain

import feature.account.domain.UserClient
import kotlinx.coroutines.flow.Flow

class GetRoomsFlow(
    private val userClient: UserClient,
    private val roomClient: RoomClient
) {
    suspend fun invoke() : Flow<List<Room>> {
        return try {
            roomClient.getRoomsFlow(userClient.getId())
        }catch (e: Exception){
            throw RuntimeException("TODO error flow")
        }
    }
}