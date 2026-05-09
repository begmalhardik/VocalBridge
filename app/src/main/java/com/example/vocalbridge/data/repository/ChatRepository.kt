package com.example.vocalbridge.data.repository

import com.example.vocalbridge.network.GroqService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    private val groqService: GroqService
) {

    suspend fun getFeedback(text: String, difficulty: String): String {

        return groqService.getSpeechFeedback(text, difficulty)
    }
}