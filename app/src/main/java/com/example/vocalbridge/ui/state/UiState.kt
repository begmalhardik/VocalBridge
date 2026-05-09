package com.example.vocalbridge.ui.state

sealed class UiState {

    object Idle : UiState()
    object Loading : UiState()
    data class Success(val response: String) : UiState()
    data class Error(val message: String) : UiState()
}