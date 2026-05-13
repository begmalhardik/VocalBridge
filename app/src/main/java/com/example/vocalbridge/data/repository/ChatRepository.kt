package com.example.vocalbridge.data.repository

import com.example.vocalbridge.data.local.SessionDao
import com.example.vocalbridge.data.model.SessionEntity
import com.example.vocalbridge.network.GroqService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    private val groqService: GroqService,
    private val sessionDao: SessionDao
) {

    suspend fun getFeedback(text: String, difficulty: String): String {

        val feedback = groqService.getSpeechFeedback(text, difficulty)

        sessionDao.insertSession(
            SessionEntity(
                spokenText = text,
                aiFeedback = feedback,
                timestamp = System.currentTimeMillis()
            )
        )

        return feedback
    }

    fun getAppSessions(): Flow<List<SessionEntity>> = sessionDao.getAllSessions()

    fun getSessionCount(): Flow<Int> = sessionDao.getSessionCount()

    fun getLastSession(): Flow<SessionEntity?> = sessionDao.getLastSession()

}