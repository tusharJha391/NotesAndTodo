package com.example.notesandtodo.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.notesandtodo.viewmodel.ToDoViewModel
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@Composable
fun ToDoScreen(
    toDoViewModel: ToDoViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val todos by toDoViewModel.todos.collectAsState()

    LaunchedEffect(key1 = Unit) {
        toDoViewModel.todos.collectLatest {
            Timber.d("Todo data list size = ${it.size}")
        }
    }
    Scaffold(modifier = modifier) { paddingValues ->
        if (todos.isEmpty()) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("No ToDo's yet. Tap + to add one.")
            }
        } else {

        }
    }
}