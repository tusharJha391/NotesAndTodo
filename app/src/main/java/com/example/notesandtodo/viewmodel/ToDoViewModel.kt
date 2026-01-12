package com.example.notesandtodo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesandtodo.database.models.TodoData
import com.example.notesandtodo.repository.NoteRepository
import com.example.notesandtodo.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(private val toDoRepository: ToDoRepository) : ViewModel() {
    val toDoLiveData = toDoRepository.toDoList
    val todos = toDoRepository.toDoList.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    fun addTodo(title: String, isDone: Boolean, priority: Int = 0, dueDate: Long? = null,) = viewModelScope.launch {
        toDoRepository.addTodo(TodoData(title = title, isDone = isDone, priority = priority, dueDate = dueDate))
    }
}