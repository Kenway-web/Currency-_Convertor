package com.music.coinswap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.music.coinswap.presentation.mainScreen.MainScreen
import com.music.coinswap.presentation.theme.CoinSwapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinSwapTheme {
                MainScreen()
            }
        }
    }
}

