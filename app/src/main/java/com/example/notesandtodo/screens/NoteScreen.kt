package com.example.notesandtodo.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesandtodo.custom.NoteItem
import com.example.notesandtodo.viewmodel.NoteViewModel
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@Composable
fun NoteScreen(
    noteViewModel: NoteViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val notes by noteViewModel.notes.collectAsState()

    LaunchedEffect(key1 = Unit) {
        noteViewModel.notes.collectLatest {
            Timber.d("note data list size = ${it.size}")
        }
    }

    Scaffold(modifier = modifier) { paddingValues ->
        if (notes.isEmpty()) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("No notes yet. Tap + to add one.")
            }
        } else {
            LazyColumn(
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(notes, key = { it.id }) { note ->
                    NoteItem(
                        note = note,
                        onClick = {
                            Toast.makeText(context, "Details of Notes Coming Soon...", Toast.LENGTH_SHORT).show()
                        },
                        onDelete = { noteViewModel.deleteNote(note) }
                    )
                }
            }
        }
    }
}