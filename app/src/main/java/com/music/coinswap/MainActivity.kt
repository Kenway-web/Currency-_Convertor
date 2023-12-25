package com.music.coinswap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.music.coinswap.presentation.mainScreen.MainScreen
import com.music.coinswap.presentation.mainScreen.MainScreenViewModel
import com.music.coinswap.presentation.theme.CoinSwapTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinSwapTheme {
                val viewModel:MainScreenViewModel = hiltViewModel()
                MainScreen(
                    state =  viewModel.state,
                    onEvent = viewModel::onEvent
                )
            }
        }
    }
}

