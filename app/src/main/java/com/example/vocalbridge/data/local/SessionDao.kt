package com.example.vocalbridge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vocalbridge.data.model.SessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(
        session: SessionEntity
    )

    @Query("""SELECT * FROM sessions ORDER BY timestamp DESC""")
    fun getAllSessions(): Flow<List<SessionEntity>>

    @Query("""SELECT COUNT(*) FROM sessions""")
    fun getSessionCount(): Flow<Int>

    @Query("""SELECT * FROM sessions ORDER BY timestamp DESC LIMIT 1""")
    fun getLastSession(): Flow<SessionEntity?>
}