package com.example.notesandtodo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesandtodo.database.models.NoteData
import com.example.notesandtodo.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {
    val noteLiveData = noteRepository.noteList
    val notes = noteRepository.noteList.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    fun addNote(title: String, description: String) = viewModelScope.launch {
        noteRepository.addNote(NoteData(title = title, description = description))
    }
    fun deleteNote(note: NoteData) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }
}