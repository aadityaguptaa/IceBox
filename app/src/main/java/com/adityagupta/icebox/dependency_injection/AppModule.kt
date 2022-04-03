package com.adityagupta.icebox.dependency_injection

import android.content.Context
import androidx.room.Room
import com.adityagupta.icebox.domain.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    @Named("room")
    fun getRoomDatabaseInstance(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        AppDatabase::class.java, "AppDatabase"
    ).build()
}