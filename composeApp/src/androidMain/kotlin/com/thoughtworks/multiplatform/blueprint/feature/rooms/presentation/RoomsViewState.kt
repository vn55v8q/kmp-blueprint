package com.thoughtworks.multiplatform.blueprint.feature.rooms.presentation

import feature.rooms.domain.Room

data class RoomsViewState(
    val isLoadingRooms: Boolean = true,
    val roomsAvailable: List<Room> = emptyList(),
)