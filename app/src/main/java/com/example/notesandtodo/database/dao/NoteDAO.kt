package com.example.notesandtodo.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesandtodo.database.models.NoteData
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Query("SELECT * from note ORDER BY timestamp DESC")
    fun getAllNotes(): Flow<List<NoteData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteData)

    @Delete
    suspend fun delete(note: NoteData)
}