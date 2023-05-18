package com.lfgtavora.pokecards.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.lfgtavora.pokecards.feature.set.navigation.cardSetsNavigationRoute
import com.lfgtavora.pokecards.feature.set.navigation.navigateToCardSets
import com.lfgtavora.pokecards.navigation.BottomBarItem
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberPokeCardAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): PokeCardAppState =
    remember(navController, coroutineScope) {
        PokeCardAppState(navController, coroutineScope)
    }

@Stable
class PokeCardAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
) {
    val bottomBarItems: List<BottomBarItem> = BottomBarItem.values().asList()

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val shouldShowBottomBar: Boolean
        @Composable get() = BottomBarItem.values().any { it.route == currentDestination?.route }


//    val currentTopLevelDestination: TopLevelDestination?
//        @Composable get() = when (currentDestination?.route) {
//            forYouNavigationRoute -> FOR_YOU
//            bookmarksRoute -> BOOKMARKS
//            interestsRoute -> INTERESTS
//            else -> null
//        }

    fun navigateToTab(bottomBarItem: BottomBarItem) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (bottomBarItem) {
            BottomBarItem.ALL_SETS -> navController.navigateToCardSets(navOptions)
        }
    }

}
