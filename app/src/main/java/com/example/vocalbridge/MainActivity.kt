package com.example.vocalbridge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vocalbridge.ui.navigation.AppNavigation
import com.example.vocalbridge.ui.theme.VocalBridgeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VocalBridgeTheme {
                AppNavigation()
            }
        }
    }
}