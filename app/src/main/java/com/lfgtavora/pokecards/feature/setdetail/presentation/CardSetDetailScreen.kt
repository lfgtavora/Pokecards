package com.lfgtavora.pokecards.feature.setdetail.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.lfgtavora.pokecards.feature.set.presentation.viewmodel.FeedUiState
import com.lfgtavora.pokecards.feature.set.presentation.viewmodel.SetViewModel

@Composable
internal fun CardSetDetailScreenRoute(
    onCardClicked: () -> Unit,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SetViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    CardSetDetailScreen(
        uiState = uiState,
        onCardClicked = onCardClicked,
        onBackClicked = onBackClicked,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSetDetailScreen(
    uiState: FeedUiState,
    onCardClicked: () -> Unit,
    onBackClicked: () -> Unit,
    modifier: Modifier
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "title") },
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(padding)
        ) {
            Text(text = "details")
        }
    }

}
