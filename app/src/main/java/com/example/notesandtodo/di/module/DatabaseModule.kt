package com.example.notesandtodo.di.module

import android.content.Context
import androidx.room.Room
import com.example.notesandtodo.database.AppDataBase
import com.example.notesandtodo.database.dao.NoteDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun providedNoteDao(dataBase: AppDataBase): NoteDAO {
        return dataBase.noteDao()
    }

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "NoteAndToDo"
        ).build()
    }
}