package com.lfgtavora.pokecards.feature.setdetail.presentation.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.lfgtavora.pokecards.R
import com.lfgtavora.pokecards.feature.set.data.domain.Card
import com.lfgtavora.pokecards.feature.set.data.domain.Images
import com.lfgtavora.pokecards.feature.set.data.response.asDomain
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

    val uiState by viewModel.uiState

    CardSetDetailScreen(
        uiState = uiState,
        name = name,
        onCardClicked = onCardClicked,
        onCardLongClick = viewModel::setCardPreview,
        onCardPreviewDismiss = { viewModel.setCardPreview(null) },
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
    onCardLongClick: (String) -> Unit,
    onCardPreviewDismiss: () -> Unit,
    onLoadMore: () -> Unit,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
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
                if (uiState.cardPreviewId != null) {
                    val currentCard = uiState.cards.first { it.id == uiState.cardPreviewId }
                    CardPreviewDialog(
                        images = currentCard.images,
                        id = currentCard.id,
                        onDismissRequest = onCardPreviewDismiss,
                        modifier = modifier
                    )
                }
                CardList(
                    cards = uiState.cards,
                    isPaginating = uiState.isPaginating,
                    onCardClick = onCardClicked,
                    onCardLongClick = onCardLongClick,
                    onLoadMore = onLoadMore,
                    modifier = Modifier.padding(padding)
                )
            }
        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardPreviewDialog(
    images: Images?,
    id: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        )
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            (LocalView.current.parent as DialogWindowProvider)?.window?.setDimAmount(4f)

            Card(
                modifier = Modifier
                    .wrapContentWidth()
                    .animateContentSize()
                    .clickable { }
            ) {
                AsyncImage(
                    model = images?.large,
                    contentDescription = "",
                    placeholder = painterResource(id = R.drawable.tcg_card_back),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "",
                    modifier = Modifier.size(10.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Click on image to view more details",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
private fun CardList(
    cards: MutableList<Card>,
    isPaginating: Boolean = false,
    onCardClick: (String) -> Unit,
    onCardLongClick: (String) -> Unit,
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
                thumbnail = card.images?.small ?: card.images?.large ?: "",
                name = card.name,
                onClick = onCardClick,
                onLongClick = onCardLongClick
            )
        }
        if (isPaginating) {
            item(span = { GridItemSpan(4) }) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        item(span = { GridItemSpan(4) }) {
            LaunchedEffect(cards.size > 0) {
                onLoadMore()
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun PokeCard(
    id: String,
    thumbnail: String,
    name: String,
    onClick: (String) -> Unit,
    onLongClick: (String) -> Unit,
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        shape = Shapes.None,
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .combinedClickable(
                onClick = { onClick(id) },
                onLongClick = { onLongClick(id) }
            )
    ) {
        AsyncImage(
            model = thumbnail,
            contentDescription = name,
            placeholder = painterResource(id = R.drawable.tcg_card_back)
        )
    }

}


@Composable
@Preview
fun CardSetDetailScreenPreview() {
    val cards: MutableList<Card> = mutableListOf()
    for (i in 1..32) {
        val card = Card(
            id = i.toString(),
            images = Images("", ""),
            name = "name",
        )
        cards.add(card)
    }

    CardSetDetailScreen(
        uiState = SetDetailUiState.Success(cards),
        name = "preview",
        onCardClicked = {},
        onCardLongClick = {},
        onCardPreviewDismiss = { },
        onLoadMore = { },
        onBackClicked = { },
    )
}

@Composable
@Preview
fun PokeCardPreview() {
    PokeCard(
        id = "000",
        thumbnail = "",
        name = "",
        onClick = {},
        onLongClick = {},
    )
}

@Composable
@Preview
private fun CardPreviewDialogPreview() {
    Surface {
        CardSetDetailScreenPreview()
        CardPreviewDialog(
            images = Images(
                "",
                ""
            ),
            id = "",
            onDismissRequest = {},
            modifier = Modifier
        )
    }
}