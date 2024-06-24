package com.thoughtworks.multiplatform.blueprint.feature.rooms.di

import com.thoughtworks.multiplatform.blueprint.feature.rooms.data.FirebaseRoomClient
import com.thoughtworks.multiplatform.blueprint.feature.rooms.presentation.RoomsViewModel
import feature.rooms.domain.CreateRoom
import feature.rooms.domain.GetRoomsFlow
import feature.rooms.domain.JoinRoom
import feature.rooms.domain.RoomClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val roomsModule = module {
    viewModel {
        RoomsViewModel(
            get(),
            get(),
            get()
        )
    }

    single {
        GetRoomsFlow(
            get(),
            get()
        )
    }

    single {
        JoinRoom(
            get(),
            get()
        )
    }

    single {
        CreateRoom(
            get(),
            get()
        )
    }

    single<RoomClient> {
        FirebaseRoomClient(
            get()
        )
    }
}