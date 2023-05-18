package com.lfgtavora.pokecards.feature.setdetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lfgtavora.pokecards.feature.setdetail.presentation.CardSetDetailScreenRoute

private const val cardSetDetailGraphRoutePattern = "card_set_detail_graph"
const val cardSetDetailNavigationRoute = "card_set_detail_route"

fun NavController.navigateToCardSetDetailGraph(navOptions: NavOptions? = null) {
    this.navigate(cardSetDetailGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.cardSetDetailScreen(
    onBackClicked: () -> Unit,
    onCardClicked: () -> Unit,
) {
    composable(route = cardSetDetailNavigationRoute) {
        CardSetDetailScreenRoute(
            onCardClicked = onCardClicked,
            onBackClicked = onBackClicked
        )
    }
}

fun NavGraphBuilder.cardSetDetailNavGraph(
//    onBackClicked: () -> Unit,
//    onCardClicked: () -> Unit,
) {
    navigation(
        route = cardSetDetailGraphRoutePattern,
        startDestination = cardSetDetailNavigationRoute
    ) {
         cardSetDetailScreen(
            onCardClicked = { },
            onBackClicked = { }
        )
    }
}
