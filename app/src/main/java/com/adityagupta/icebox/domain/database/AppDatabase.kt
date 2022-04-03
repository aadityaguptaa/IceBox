package com.adityagupta.icebox.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adityagupta.icebox.data.database.Subject

@Database(entities = [Subject::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun dao(): MainDao
}