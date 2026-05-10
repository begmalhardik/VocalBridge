package com.example.vocalbridge.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vocalbridge.ui.state.UiState
import com.example.vocalbridge.viewmodel.ChatViewModel

@Composable
fun PracticeScreen(
    viewModel: ChatViewModel = hiltViewModel()
) {

    var text by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Enter speech text")
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                viewModel.getFeedback(
                    text,
                    "Beginner"
                )
            }
        ) {
            Text("Get Feedback")
        }

        Spacer(modifier = Modifier.height(20.dp))

        when (uiState) {

            UiState.Idle -> {}

            UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Success -> {

                Text(
                    text = (uiState as UiState.Success).response
                )
            }

            is UiState.Error -> {

                Text(
                    text = (uiState as UiState.Error).message
                )
            }
        }
    }
}