package com.example.vocalbridge.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sessions")
data class SessionEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val spokenText: String,
    val aiFeedback: String,
    val timestamp: Long
)