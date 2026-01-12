package com.example.notesandtodo.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.notesandtodo.database.models.TodoData
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDAO {
    @Query("SELECT * FROM todos ORDER BY createdAt DESC")
    fun getTodos(): Flow<List<TodoData>>

    @Insert
    suspend fun insert(todo: TodoData)

    @Delete
    suspend fun delete(todo: TodoData)

    @Query("UPDATE todos SET isDone = :done WHERE id = :id")
    suspend fun updateDone(id: Int, done: Boolean)
}