package com.lfgtavora.pokecards.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.lfgtavora.pokecards.navigation.BottomBarItem
import com.lfgtavora.pokecards.navigation.PokeCardsNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeCardApp(
    appState: PokeCardAppState = rememberPokeCardAppState()
) {
    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = appState.shouldShowBottomBar,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
            ) {
                BottomAppBar {
                    PokeCardsBottomBar(
                        items = appState.bottomBarItems,
                        onNavigateToTab = appState::navigateToTab,
                        currentDestination = appState.currentDestination,
                        modifier = Modifier
                    )
                }
            }
        }
    ) { padding ->
        Row(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
        ) {
            PokeCardsNavHost(navController = appState.navController)
        }
    }
}

@Composable
private fun PokeCardsBottomBar(
    items: List<BottomBarItem>,
    onNavigateToTab: (BottomBarItem) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    items.forEach { item ->
        Row {
            val selected = currentDestination.isActiveRoute(item.route)
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToTab(item) },
                icon = {
                    val icon = if (selected) {
                        item.selectedIcon
                    } else {
                        item.unselectedIcon
                    }
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                    )
                },
                modifier = modifier,
                enabled = true,
                label = { Text(stringResource(item.iconTextId)) },
                alwaysShowLabel = true,

                )
        }
    }
}

private fun NavDestination?.isActiveRoute(destination: String) =
    this?.hierarchy?.any {
        it.route?.equals(destination) ?: false
    } ?: false


@Preview
@Composable
private fun PokeCardsBottomBarPreview() {
    BottomAppBar {
        PokeCardsBottomBar(
            items = BottomBarItem.values().asList(),
            onNavigateToTab = {},
            currentDestination = null,
        )
    }
}