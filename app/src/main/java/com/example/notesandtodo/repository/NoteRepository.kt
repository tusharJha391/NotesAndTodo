package com.example.notesandtodo.repository

import com.example.notesandtodo.database.dao.NoteDAO
import com.example.notesandtodo.database.models.NoteData
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDAO: NoteDAO) {

    val noteList: Flow<List<NoteData>> = noteDAO.getAllNotes()
}