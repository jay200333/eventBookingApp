package com.example.eventbookingapp.view.main.components

import androidx.compose.foundation.clickable
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import com.example.eventbookingapp.MyPageScreenRoute
import com.example.eventbookingapp.R

@Composable
fun BottomNavIcon(
    icon: Painter,
    description: String? = null
) {
    Icon(
        painter = icon,
        contentDescription = description
    )
}