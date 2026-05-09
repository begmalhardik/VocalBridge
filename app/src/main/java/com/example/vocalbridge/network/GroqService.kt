package com.example.vocalbridge.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import com.example.vocalbridge.BuildConfig
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroqService @Inject constructor() {

    private val client = OkHttpClient()

    suspend fun getSpeechFeedback(
        userText: String,
        difficulty: String
    ): String {

        return withContext(Dispatchers.IO) {

            try {

                val prompt = """
                    User said: '$userText'
                    Difficulty level: $$difficulty

                    You are a professional speech articulation coach.
                    Give short, encouraging pronunciation feedback.
                """.trimIndent()

                val json = JSONObject().apply {
                    put("model", "llama-3.1-8b-instant")

                    put("messages", JSONArray().apply {

                        put(JSONObject().apply {
                            put("role", "user")
                            put("content", prompt)
                        })
                    })
                }

                val body = json.toString().toRequestBody("application/json".toMediaType())

                val request = Request.Builder()
                    .url("https://api.groq.com/openai/v1/chat/completions")
                    .addHeader(
                        "Authorization",
                        "Bearer ${BuildConfig.GROQ_API_KEY}"
                    )
                    .addHeader(
                        "Content-Type",
                        "application/json"
                    )
                    .post(body)
                    .build()

                val response = client.newCall(request).execute()

                val responseBody = response.body?.string() ?: return@withContext "Empty response"

                val result = JSONObject(responseBody)

                result
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content")

            } catch (e: Exception) {

                e.message ?: "Unknown Error"
            }
        }
    }
}