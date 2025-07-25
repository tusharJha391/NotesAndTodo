package com.example.notesandtodo.repository

import com.example.notesandtodo.database.dao.NoteDAO
import com.example.notesandtodo.database.models.NoteData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(noteDAO: NoteDAO) {

    val noteList: Flow<List<NoteData>> = noteDAO.getAllNotes()
}