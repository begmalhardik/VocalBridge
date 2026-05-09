package com.example.vocalbridge.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vocalbridge.ui.navigation.AppNavigation
import com.example.vocalbridge.ui.theme.VocalBridgeTheme

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