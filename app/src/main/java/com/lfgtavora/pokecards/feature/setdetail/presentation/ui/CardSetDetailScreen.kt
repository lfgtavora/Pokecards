package com.lfgtavora.pokecards.feature.setdetail.presentation.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.lfgtavora.pokecards.feature.set.data.response.CardDto
import com.lfgtavora.pokecards.feature.setdetail.presentation.viewmodel.CardSetDetailViewModel
import com.lfgtavora.pokecards.feature.setdetail.presentation.viewmodel.SetDetailUiState
import com.lfgtavora.pokecards.R

@Composable
internal fun CardSetDetailScreenRoute(
    name: String,
    onCardClicked: (String) -> Unit,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CardSetDetailViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState

    CardSetDetailScreen(
        uiState = uiState,
        name = name,
        onCardClicked = onCardClicked,
        onLoadMore = viewModel::loadNextItems,
        onBackClicked = onBackClicked,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSetDetailScreen(
    uiState: SetDetailUiState,
    name: String,
    onCardClicked: (String) -> Unit,
    onLoadMore: () -> Unit,
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
                title = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AsyncImage(
                            model = "https://images.pokemontcg.io/sv1/logo.png",
                            contentDescription = name,
                            modifier = Modifier.width(100.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "Information about set"
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Green,
                )
            )

        },
    ) { padding ->

        when (uiState) {
            is SetDetailUiState.Loading -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            is SetDetailUiState.Error -> {
                Text(text = "Error: ${uiState.message}")
            }

            is SetDetailUiState.Success -> {
                CardList(
                    cards = uiState.cards,
                    isPaginating = uiState.isPaginating,
                    onCardClick = onCardClicked,
                    onLoadMore = onLoadMore,
                    modifier = Modifier.padding(padding)
                )
            }
        }


    }


}

@Composable
private fun CardList(
    cards: MutableList<CardDto>,
    isPaginating: Boolean = false,
    onCardClick: (String) -> Unit,
    onLoadMore: () -> Unit,
    modifier: Modifier
) {

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
                onClick = onCardClick
            )
        }
        item {
            LaunchedEffect(cards.size > 0) {
                Log.i("paging", "CardList: reachedend")
                onLoadMore()
            }
        }
        item {
            if (isPaginating) {
                CircularProgressIndicator()
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokeCard(
    id: String,
    thumbnail: String,
    name: String,
    onClick: (String) -> Unit
) {

    Card(
        shape = Shapes.None,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        modifier = Modifier.clickable { onClick(id) }
    ) {
        AsyncImage(
            model = thumbnail,
            contentDescription = name,
            placeholder = painterResource(id = R.drawable.tcg_card_back)
        )
    }

}

//@Composable
//@Preview
//fun CardSetDetailScreenPreview() {
//    CardSetDetailScreen(
//        cards = LazyPagingItems.,
//        name = "set title",
//        onCardClicked = { },
//        onBackClicked = { },
//        modifier = Modifier
//    )
//}

@Composable
@Preview
fun PokeCardPreview() {
    PokeCard(
        id = "000",
        thumbnail = "https://images.pokemontcg.io/sv1/31.png",
        name = "",
        onClick = {}
    )
}
