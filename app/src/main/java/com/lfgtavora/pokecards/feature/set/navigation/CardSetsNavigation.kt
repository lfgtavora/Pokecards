package com.lfgtavora.pokecards.feature.set.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.lfgtavora.pokecards.feature.set.presentation.ui.CardSetsScreenRoute

const val cardSetsNavigationRoute = "all_sets_route"

fun NavController.navigateToCardSets(navOptions: NavOptions? = null) {
    this.navigate(cardSetsNavigationRoute, navOptions)
}

fun NavGraphBuilder.cardSetsScreen(
    onCardClicked: () -> Unit,
) {
    composable(route = cardSetsNavigationRoute) {
        CardSetsScreenRoute(onCardClicked = onCardClicked)
    }
}

