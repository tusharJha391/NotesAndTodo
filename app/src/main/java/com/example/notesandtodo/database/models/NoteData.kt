package com.example.notesandtodo.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
class NoteData {
    @PrimaryKey
    var id: Int = 1
    var title: String = ""
    var description: String = ""
    var date: String = ""
    var time: Long = 0L
}