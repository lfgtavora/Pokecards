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
import com.lfgtavora.pokecards.feature.set.data.response.CardSetDto
import com.lfgtavora.pokecards.feature.set.presentation.viewmodel.FeedUiState
import com.lfgtavora.pokecards.feature.set.presentation.viewmodel.SetViewModel
import java.text.SimpleDateFormat

@Composable
internal fun CardSetsScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: SetViewModel = hiltViewModel(),
) {
    val feedState by viewModel.uiState.collectAsState()

    CardSetsScreen(
        feedState = feedState,
        onClick = viewModel::navigateToSetDetails,
        modifier = modifier
    )
}

@Composable
fun CardSetsScreen(
    feedState: FeedUiState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (feedState) {

            is FeedUiState.Success -> CardSetsList(
                cardSetList = feedState.cardSets,
                onClick = onClick
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
