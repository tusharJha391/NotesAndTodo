package com.example.notesandtodo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesandtodo.navigation.NavRoutes
import com.example.notesandtodo.screens.AddNoteScreen
import com.example.notesandtodo.screens.AddTodoScreen
import com.example.notesandtodo.screens.AppMainScreen
import com.example.notesandtodo.viewmodel.NoteViewModel
import com.example.notesandtodo.viewmodel.ToDoViewModel

@Composable
fun MainNavigation(noteViewModel: NoteViewModel, toDoViewModel: ToDoViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.Home.route) {
        composable(NavRoutes.Home.route) {
            AppMainScreen(
                modifier = Modifier.fillMaxSize(),
                noteViewModel, toDoViewModel,navController
            )
        }

        composable(NavRoutes.AddNote.route) {
            AddNoteScreen(noteViewModel, navController) {
                navController.popBackStack()
            }
        }

        composable(NavRoutes.AddTodo.route) {
            AddTodoScreen(toDoViewModel) {
                navController.popBackStack()
            }
        }
    }
}