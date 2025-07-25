package com.example.notesandtodo.viewmodel

import androidx.lifecycle.ViewModel
import com.example.notesandtodo.repository.NoteRepository

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    val noteLiveData = noteRepository.noteList
}