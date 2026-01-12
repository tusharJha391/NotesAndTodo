package com.example.notesandtodo.repository

import com.example.notesandtodo.database.dao.NoteDAO
import com.example.notesandtodo.database.dao.ToDoDAO
import com.example.notesandtodo.database.models.NoteData
import com.example.notesandtodo.database.models.TodoData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToDoRepository @Inject constructor(private val toDoDAO: ToDoDAO) {

    val toDoList: Flow<List<TodoData>> = toDoDAO.getTodos()

    suspend fun addTodo(todo: TodoData) = toDoDAO.insert(todo)
    suspend fun updateTodo(id: Int, done: Boolean) = toDoDAO.updateDone(id, done)
}