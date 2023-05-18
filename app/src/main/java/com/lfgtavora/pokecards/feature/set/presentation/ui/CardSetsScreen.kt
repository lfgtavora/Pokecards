package com.lfgtavora.pokecards.feature.set.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.lfgtavora.pokecards.feature.set.data.domain.CardSet
import com.lfgtavora.pokecards.feature.set.presentation.viewmodel.FeedUiState
import com.lfgtavora.pokecards.feature.set.presentation.viewmodel.SetViewModel

@Composable
internal fun CardSetsScreenRoute(
    onCardClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SetViewModel = hiltViewModel(),
) {
    val feedState by viewModel.uiState.collectAsState()

    CardSetsScreen(
        feedState = feedState,
        onCardClicked = onCardClicked,
        modifier = modifier
    )
}

@Composable
fun CardSetsScreen(
    feedState: FeedUiState,
    onCardClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (feedState) {

            is FeedUiState.Success -> CardSetsList(
                cardSetList = feedState.cardSets,
                onClick = onCardClicked
            )
            is FeedUiState.Error -> Text(text = "deu ruim")
            is FeedUiState.Loading -> Column(
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
fun CardSetsList(
    cardSetList: List<CardSet>,
    onClick: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(
            items = cardSetList,
            key = { item -> item.id }
        ) { item ->
            CardSetItem(onClick, item)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun CardSetItem(
    onClick: () -> Unit,
    item: CardSet
) {
    Card(modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clickable { onClick() }) {
        Column(
            modifier = Modifier.height(100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            AsyncImage(
                model = item.logo,
                contentDescription = "logo of ${item.name} set",
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.height(36.dp)
            )
            Text(
                text = "${item.total} cards",
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}
