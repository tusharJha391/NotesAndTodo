package com.example.notesandtodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notesandtodo.screens.AppMainScreen
import com.example.notesandtodo.ui.theme.NotesAndTodoTheme
import com.example.notesandtodo.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

val LocalNavHostController =
    compositionLocalOf<NavHostController> { error("Unable to provide NavHostController") }

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val noteViewModel: NoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAndTodoTheme {
                val navHostController = rememberNavController()
                CompositionLocalProvider(LocalNavHostController provides navHostController) {
                    AppMainScreen(
                        modifier = Modifier.fillMaxSize(),
                        noteViewModel
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    NotesAndTodoTheme {
        AppMainScreen()
    }
}