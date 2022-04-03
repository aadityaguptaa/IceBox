package com.adityagupta.icebox.domain.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.adityagupta.icebox.data.database.Subject

@Dao
interface MainDao {

    @Query("Select * from Subject")
    fun getSubjects(): List<Subject>

    @Insert
    fun addSubject(subject: Subject)

    @Delete
    fun deleteSubject(subject: Subject)
}
