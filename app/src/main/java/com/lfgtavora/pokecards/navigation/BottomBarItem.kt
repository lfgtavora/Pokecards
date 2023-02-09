package com.lfgtavora.pokecards.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.lfgtavora.pokecards.R

enum class BottomBarItem(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    ALL_SETS(
        route= ALL_SETS_ROUTE,
        selectedIcon = Icons.Outlined.List,
        unselectedIcon = Icons.Outlined.List,
        iconTextId = R.string.sets,
        titleTextId = R.string.sets,
    )
}