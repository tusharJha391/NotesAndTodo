package com.example.notesandtodo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesandtodo.screens.AppMainScreen
import com.example.notesandtodo.viewmodel.NoteViewModel

@Composable
fun MainNavigation(viewModel: NoteViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Main") {
        composable("Main") {
            AppMainScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel, navController
            )
        }

    }
}