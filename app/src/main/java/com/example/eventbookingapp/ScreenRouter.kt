package com.example.eventbookingapp

import androidx.compose.ui.graphics.painter.Painter
import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenRouter(val iconId: Int)

@Serializable
data object HomeScreenRoute : ScreenRouter(R.drawable.home_filled)

@Serializable
data object MapScreenRoute : ScreenRouter(R.drawable.map_filled)

@Serializable
data object MyPageScreenRoute : ScreenRouter(R.drawable.group_filled)