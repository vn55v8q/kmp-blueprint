package com.thoughtworks.multiplatform.blueprint.feature.rooms.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.avatar.domain.GetAvatar
import feature.rooms.domain.CreateRoom
import feature.rooms.domain.GetRoomsFlow
import feature.rooms.domain.JoinRoom
import feature.rooms.domain.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RoomsViewModel(
    private val getRooms: GetRoomsFlow,
    private val createRoom: CreateRoom,
    private val joinRoom: JoinRoom
) : ViewModel() {

    private val mutableStateFlow = MutableStateFlow(RoomsViewState())
    val state = mutableStateFlow.asStateFlow()

    fun fetch() {
        viewModelScope.launch {
            getRooms.invoke().collect { roomsUpdate ->
                mutableStateFlow.update {
                    it.copy(
                        isLoadingRooms = false,
                        roomsAvailable = roomsUpdate
                    )
                }
            }
        }
    }

    fun createRoom() {
        viewModelScope.launch {
            createRoom.invoke()
        }
    }

    fun joinRoom(room: Room) {
        viewModelScope.launch {
            joinRoom.invoke(room.id)
        }
    }
}

