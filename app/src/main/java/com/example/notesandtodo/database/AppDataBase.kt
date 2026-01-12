package com.example.notesandtodo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesandtodo.database.dao.NoteDAO
import com.example.notesandtodo.database.dao.ToDoDAO
import com.example.notesandtodo.database.models.NoteData
import com.example.notesandtodo.database.models.TodoData

@Database(entities = [NoteData::class, TodoData::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDAO
    abstract fun toDoDao(): ToDoDAO

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "NoteAndToDo"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}