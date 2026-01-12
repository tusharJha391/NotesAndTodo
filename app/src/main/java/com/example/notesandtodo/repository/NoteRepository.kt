package com.example.notesandtodo.repository

import com.example.notesandtodo.database.dao.NoteDAO
import com.example.notesandtodo.database.models.NoteData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDAO: NoteDAO) {

    val noteList: Flow<List<NoteData>> = noteDAO.getAllNotes()

    suspend fun addNote(note: NoteData) = noteDAO.insert(note)
    suspend fun deleteNote(note: NoteData) = noteDAO.delete(note)
}