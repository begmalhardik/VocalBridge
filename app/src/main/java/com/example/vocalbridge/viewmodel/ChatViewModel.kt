package com.example.vocalbridge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocalbridge.data.repository.ChatRepository
import com.example.vocalbridge.network.GroqService
import com.example.vocalbridge.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: ChatRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState

    fun getFeedback(text: String, difficulty: String) {

        if (text.isBlank()) {

            _uiState.value = UiState.Error("Please enter text")
            return
        }

        viewModelScope.launch {

            _uiState.value = UiState.Loading

            try {

                val response = repository.getFeedback(text, difficulty)

                _uiState.value = UiState.Success(response)
            } catch (e: Exception) {

                _uiState.value = UiState.Error(
                    e.message ?: "Unknown Error"
                )
            }
        }
    }
}