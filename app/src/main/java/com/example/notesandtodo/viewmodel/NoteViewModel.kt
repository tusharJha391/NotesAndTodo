package com.example.notesandtodo.viewmodel

import androidx.lifecycle.ViewModel
import com.example.notesandtodo.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(noteRepository: NoteRepository) : ViewModel() {
    val noteLiveData = noteRepository.noteList
}