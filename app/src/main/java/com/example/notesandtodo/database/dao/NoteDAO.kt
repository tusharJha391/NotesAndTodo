package com.example.notesandtodo.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.notesandtodo.database.models.NoteData
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Query("SELECT * from note")
    fun getAllNotes(): Flow<List<NoteData>>
}