package com.example.notesandtodo.navigation

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object AddNote : NavRoutes("add_note")
    object AddTodo : NavRoutes("add_todo")
}