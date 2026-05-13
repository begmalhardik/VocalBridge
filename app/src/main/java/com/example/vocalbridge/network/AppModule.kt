package com.example.vocalbridge.network

import android.content.Context
import androidx.room.Room
import com.example.vocalbridge.data.datastore.UserPreferencesManager
import com.example.vocalbridge.data.local.AppDatabase
import com.example.vocalbridge.data.local.SessionDao
import com.example.vocalbridge.data.repository.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGroqService(): GroqService = GroqService()

    @Provides
    @Singleton
    fun provideChatRepository(groqService: GroqService): ChatRepository = ChatRepository(groqService)

    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferencesManager {

        return UserPreferencesManager(context)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : AppDatabase {

        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "vocal_bridge_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSessionDao(
        database: AppDatabase
    ): SessionDao {

        return database.sessionDao()
    }
}