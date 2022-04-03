package com.adityagupta.icebox.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Subject")
data class Subject (
    @PrimaryKey(autoGenerate = true) val subjectId: Int?,
    @ColumnInfo(name= "subjectName") val subjectName: String?
)