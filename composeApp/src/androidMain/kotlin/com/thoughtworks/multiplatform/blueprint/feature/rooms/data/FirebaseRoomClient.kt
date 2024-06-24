package com.thoughtworks.multiplatform.blueprint.feature.rooms.data

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import feature.avatar.domain.Avatar
import feature.rooms.domain.Room
import feature.rooms.domain.RoomClient
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import platform.log.Log

class FirebaseRoomClient(
    private val firestore: FirebaseFirestore
) : RoomClient {

    private var userIdCache = ""

    companion object {
        private const val ROOMS_COLLECTION = "rooms"
    }

    override fun getRoomsFlow(userId: String): Flow<List<Room>> = callbackFlow {
        userIdCache = userId
        val roomsCollection = firestore.collection(ROOMS_COLLECTION)
            .whereEqualTo("active", true) // Solo salas activas

        val listener = roomsCollection.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                close(exception) // Cerrar el flujo con error
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val roomsList = mutableListOf<Room>()

                snapshot.documents.forEach { roomDocument ->
                    val id = roomDocument.getString("id") ?: ""
                    val createdId = roomDocument.getString("userCreatedId") ?: ""
                    val usersList =
                        roomDocument.get("users") as? List<Map<String, Any>> ?: emptyList()

                    val avatars = usersList.mapNotNull { userMap ->
                        try {
                            // Convertir el Map a un objeto Avatar
                            Avatar(
                                id = userMap["id"] as? String ?: "",
                                name = userMap["name"] as? String ?: "",
                                urlImage = userMap["avatarUrl"] as? String ?: ""
                            )
                        } catch (e: Exception) {
                            null // Si hay un error en la conversión, ignorar ese usuario
                        }
                    }
                    Log.d("Rooms", "userId: $userIdCache")

                    val userNotExist = avatars.count {
                        Log.d("Rooms", "it.id: $userIdCache")
                        it.id == userIdCache
                    } == 0
                    Log.d("Rooms", "userNotExist: $userNotExist")
                    val room = Room(id, createdId, userNotExist, avatars)
                    roomsList.add(room)

                    if (roomsList.size == snapshot.documents.size) {
                        trySend(roomsList.toList()) // Enviar la lista completa al flujo
                    }
                }
            }
        }
        // Asegurar que el listener se quite cuando el flujo se cierra
        awaitClose { listener.remove() }
    }


    override suspend fun create(avatar: Avatar): Room {
        try {
            val roomId = firestore.collection(ROOMS_COLLECTION).document().id
            val newRoom = RoomModel(
                id = roomId,
                active = true,
                userCreatedId = avatar.id,
                created = Timestamp.now(),
                users = listOf(avatar) // Inicializar la lista de usuarios con el creador de la sala
            )

            firestore.collection(ROOMS_COLLECTION).document(roomId).set(newRoom).await()

            return Room(newRoom.id, avatar.id, false, listOf(avatar))
        } catch (e: Exception) {
            return Room("", "", false, emptyList())
        }
    }


    override suspend fun join(idRoom: String, avatar: Avatar): Room {
        val roomRef = firestore.collection(ROOMS_COLLECTION).document(idRoom)
        val roomSnapshot = roomRef.get().await()

        if (roomSnapshot.exists()) {
            val room = roomSnapshot.toObject(RoomModel::class.java)

            if (room != null) {
                // Obtener la lista de usuarios
                val usersList = room.users.toMutableList()
                val userNotExist = usersList.count { it.id == avatar.id } == 0
                if (usersList.size < 4 && userNotExist) {
                    // Añadir el avatar a la lista de usuarios si hay menos de 4 usuarios
                    usersList.add(avatar)
                    roomRef.update("users", usersList).await()
                }

                return Room(room.id, room.userCreatedId, false, usersList)
            } else {
                throw Exception("Failed to parse RoomModel from Firestore snapshot")
            }
        } else {
            throw Exception("Room with id $idRoom does not exist")
        }
    }

}