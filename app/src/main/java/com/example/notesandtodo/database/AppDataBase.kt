package com.example.notesandtodo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesandtodo.database.dao.NoteDAO
import com.example.notesandtodo.database.models.NoteData

@Database(entities = [NoteData::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDAO

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "NoteAndToDo"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}