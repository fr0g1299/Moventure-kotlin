package com.fr0g.moventure.di

import android.content.Context
import androidx.room.Room
import com.fr0g.moventure.common.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "movie_db").build()
    }

    @Provides
    fun provideWatchlistDao(db: AppDatabase) = db.watchlistDao()
}