package com.thoughtworks.multiplatform.blueprint.feature.rooms.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thoughtworks.multiplatform.blueprint.feature.rooms.presentation.RoomsViewState
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.image.CircularWebImage
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.spacer.MediumSpacer
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.text.TitleMediumText
import feature.avatar.domain.Avatar
import feature.rooms.domain.Room

@Composable
fun RoomsScreen(
    state: RoomsViewState,
    onCreate: () -> Unit,
    onJoin: (Room) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreate
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn {
                state.roomsAvailable.forEach { room ->
                    item {
                        RoomComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            room = room,
                            onClickRoom = onJoin
                        )
                        MediumSpacer()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RoomComponent(
    modifier: Modifier = Modifier,
    room: Room,
    onClickRoom: (Room) -> Unit
) {
    Card(
        modifier = modifier,
    ) {
        Column {
            TitleMediumText(text = room.id)
            TitleMediumText(text = "isJoined: ${room.isJoinedRoom}")
            TitleMediumText(text = "Users: ${room.users.size}")
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        onClickRoom(room)
                    })
            ) {
                room.users.forEach { avatarItem ->
                    AvatarComponent(
                        modifier = Modifier.size(80.dp),
                        avatar = avatarItem
                    ) {}
                }
            }
        }
    }
}

@Composable
fun AvatarComponent(
    modifier: Modifier = Modifier,
    avatar: Avatar,
    onClick: () -> Unit
) {
    CircularWebImage(
        modifier = modifier,
        url = avatar.urlImage,
        onClick = onClick
    )
    TitleMediumText(text = avatar.user)
}