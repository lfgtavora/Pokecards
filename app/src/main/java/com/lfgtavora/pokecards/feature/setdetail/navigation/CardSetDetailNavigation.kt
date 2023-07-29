package com.lfgtavora.pokecards.feature.setdetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lfgtavora.pokecards.feature.setdetail.presentation.ui.CardSetDetailScreenRoute

const val cardSetDetailNavigationRoute = "card_set_detail_route"

fun NavController.navigateToCardSetDetailScreen(
    id: String,
    name: String,
    navOptions: NavOptions? = null
) {
    this.navigate("$cardSetDetailNavigationRoute/$id/$name", navOptions)
}

fun NavGraphBuilder.cardSetDetailScreen(
    onBackClicked: () -> Unit,
    onCardClicked: (String) -> Unit,
) {
    composable(
        route = "$cardSetDetailNavigationRoute/{id}/{name}",
        arguments = listOf(
            navArgument("id") { type = NavType.StringType },
            navArgument("name") { type = NavType.StringType }
        )
    ) {
        val name = it.arguments?.getString("name") ?: ""

        CardSetDetailScreenRoute(
            name = name,
            onCardClicked = onCardClicked,
            onBackClicked = onBackClicked
        )
    }
}
