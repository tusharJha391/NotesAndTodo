package com.example.notesandtodo.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteData (
    @PrimaryKey
    var id: Int = 1,
    var title: String = "",
    var description: String = "",
    var date: String = "",
    val color: String = "#FFFFFF",
    val timestamp: Long = System.currentTimeMillis()
)