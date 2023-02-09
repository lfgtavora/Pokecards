package com.lfgtavora.pokecards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lfgtavora.pokecards.ui.PokeCardApp
import com.lfgtavora.pokecards.ui.theme.PokecardsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokecardsTheme {
                PokeCardApp()
//                val navController = rememberNavController()
//                NavHost(
//                    navController = navController,
//                    startDestination = "decks_list_screen"
//                ) {
//                    composable(
//                        route = "decks_list_screen"
//                    ) {
//
//                    }
//                    composable(
//                        route = "deck_detail_screen"
//                    ) {
//
//                    }
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokecardsTheme {
        Greeting("Android")
    }
}