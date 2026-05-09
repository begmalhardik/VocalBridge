package com.example.vocalbridge.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferencesManager(private val context: Context) {

    companion object {
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_LEVEL = stringPreferencesKey("user_level")
        val USER_LANGUAGE = stringPreferencesKey("user_language")
    }

    suspend fun saveUser(
        name: String,
        level: String,
        language: String
    ) {
        context.dataStore.edit {
            it[USER_NAME] = name
            it[USER_LEVEL] = level
            it[USER_LANGUAGE] = language
        }
    }

    val userName: Flow<String> = context.dataStore.data.map { it[USER_NAME] ?: "" }
    val userLevel: Flow<String> = context.dataStore.data.map { it[USER_LEVEL] ?: "" }
    val userLanguage: Flow<String> = context.dataStore.data.map { it[USER_LANGUAGE] ?: "English" }

}