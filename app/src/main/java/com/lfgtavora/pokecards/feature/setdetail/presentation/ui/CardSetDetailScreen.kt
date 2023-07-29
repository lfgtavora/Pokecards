package com.lfgtavora.pokecards.feature.setdetail.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.lfgtavora.pokecards.feature.set.data.local.CardEntity
import com.lfgtavora.pokecards.feature.set.data.response.CardDto
import com.lfgtavora.pokecards.feature.setdetail.presentation.viewmodel.CardSetDetailViewModel
import com.lfgtavora.pokecards.feature.setdetail.presentation.viewmodel.SetDetailUiState

@Composable
internal fun CardSetDetailScreenRoute(
    name: String,
    onCardClicked: (String) -> Unit,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CardSetDetailViewModel = hiltViewModel(),
) {

    val cards = viewModel.paginateCards.collectAsLazyPagingItems()

    CardSetDetailScreen(
        cards = cards,
        name = name,
        onCardClicked = onCardClicked,
        onBackClicked = onBackClicked,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSetDetailScreen(
    cards: LazyPagingItems<CardEntity>,
    name: String,
    onCardClicked: (String) -> Unit,
    onBackClicked: () -> Unit,
    modifier: Modifier
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onBackClicked,
                        content = {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "back"
                            )
                        })
                },
                title = { Text(text = name) },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "Information about set"
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { padding ->
        when (cards) {
            is SetDetailUiState.Success -> CardList(
                cards = cards.cards,
                onCardClick = onCardClicked,
                onReachEnd = onReachEnd,
                modifier = Modifier.padding(padding)
            )

            is SetDetailUiState.Error -> Text(text = "deu ruim")
            is SetDetailUiState.Loading -> Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }

}

@Composable
private fun CardList(
    cards: List<CardDto>,
    onCardClick: (String) -> Unit,
    modifier: Modifier
) {
    val scrollState = rememberScrollState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.fillMaxHeight(),
    ) {
        items(
            items = cards,
            key = { card -> card.id }
        ) { card ->
            PokeCard(
                id = card.id,
                thumbnail = card.images.small,
                name = card.name,
                modifier = modifier,
                onClick = onCardClick
            )
        }
        item {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokeCard(
    id: String,
    thumbnail: String,
    name: String,
    modifier: Modifier,
    onClick: (String) -> Unit
) {

    AsyncImage(
        model = thumbnail,
        contentDescription = name
    )

}

@Composable
@Preview
fun CardSetDetailScreenPreview() {
    CardSetDetailScreen(
        cards = SetDetailUiState.Loading,
        name= "set title",
        onCardClicked = { },
        onBackClicked = { },
        modifier = Modifier
    )
}

@Composable
@Preview
fun PokeCardPreview() {
    PokeCard(
        id = "000",
        thumbnail = "https://images.pokemontcg.io/sv1/31.png",
        name = "",
        modifier = Modifier,
        onClick = {}
    )
}
