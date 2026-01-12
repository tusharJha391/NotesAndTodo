package com.example.notesandtodo.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesandtodo.bottommenu.MainBottomBar
import com.example.notesandtodo.navigation.NavRoutes
import com.example.notesandtodo.ui.theme.NotesAndTodoTheme
import com.example.notesandtodo.viewmodel.NoteViewModel
import com.example.notesandtodo.viewmodel.ToDoViewModel
import timber.log.Timber

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppMainScreen(
    modifier: Modifier = Modifier, noteViewModel: NoteViewModel? = null,
    toDoViewModel: ToDoViewModel? = null, navController: NavController
) {
    val context = LocalContext.current
    val currentBottomMenuSelection = remember { mutableIntStateOf(0) }
    val topTitle = remember { mutableStateOf("Note") }
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopAppbar(
                topTitle.value,
                currentTabValue = currentBottomMenuSelection.intValue,
                navController = navController
            )
        },
        bottomBar = {
            MainBottomBar(onClick = { id ->
                Timber.d("click id is = $id")
                currentBottomMenuSelection.intValue = id
            })
        },
        floatingActionButton = {
            FloatingAddButton(
                navController,
                currentBottomMenuSelection.intValue
            )
        }
    ) { innerPadding ->
        if (currentBottomMenuSelection.intValue == 0) {
            topTitle.value = "Note's"
            noteViewModel?.let {
                NoteScreen(
                    it,
                    navController,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }
        } else if (currentBottomMenuSelection.intValue == 1) {
            topTitle.value = "ToDo's"
            toDoViewModel?.let {
                ToDoScreen(
                    it, navController = navController,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppbar(
    title: String,
    isBackButtonVisible: Boolean = false,
    isAddButtonVisible: Boolean = true,
    currentTabValue: Int = 0,
    navController: NavController
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            if (isBackButtonVisible) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            if (isAddButtonVisible) {
                IconButton(onClick = {
                    Timber.d("Top plus click id is === ")
                    if (currentTabValue == 0) {
                        // Add Notes
                        navController.navigate(NavRoutes.AddNote.route)
                    } else if (currentTabValue == 1) {
                        // Add ToDos
                        navController.navigate(NavRoutes.AddTodo.route)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = ""
                    )
                }
            }
        },
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
    )
}

@Composable
fun FloatingAddButton(navController: NavController, currentTabValue: Int) {
    ExtendedFloatingActionButton(
        onClick = {
            Timber.d("Add Floating Action Button CLICKED ++++++ == $currentTabValue")
            if (currentTabValue == 0) {
                // Add Notes
                navController.navigate(NavRoutes.AddNote.route)
            } else if (currentTabValue == 1) {
                // Add ToDos
                navController.navigate(NavRoutes.AddTodo.route)
            }
        },
    ) {
        Row {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Add", modifier = Modifier.align(Alignment.CenterVertically))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppMainPreview() {
    NotesAndTodoTheme {
        AppMainScreen(navController = rememberNavController())
    }
}