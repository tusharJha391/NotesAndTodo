package com.example.notesandtodo.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesandtodo.bottommenu.MainBottomBar
import com.example.notesandtodo.ui.theme.NotesAndTodoTheme
import com.example.notesandtodo.viewmodel.NoteViewModel
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppMainScreen(modifier: Modifier = Modifier, noteViewModel: NoteViewModel? = null) {
    val navigationHost = LocalContentColor.current
    val context = LocalContext.current
    var currentBottomMenuSelection = remember { mutableIntStateOf(0) }
    LaunchedEffect(key1 = Unit) {
        noteViewModel?.noteLiveData?.collectLatest {
            Timber.d("note data = ${it.size}")
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = { MainTopAppbar() },
        bottomBar = {
            MainBottomBar(onClick = { id ->
                Timber.d("click id is = $id")
                currentBottomMenuSelection.intValue = id
            })
        },
        floatingActionButton = { FloatingAddButton() }
    ) { innerPadding ->
        if (currentBottomMenuSelection.intValue == 0) {
            NoteScreen()
        } else if (currentBottomMenuSelection.intValue == 1) {
            ToDoScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppbar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(
                text = "Note",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
//        navigationIcon = {
//            IconButton(onClick = {}) {
//                Icon(
//                    imageVector = Icons.Default.Menu,
//                    contentDescription = ""
//                )
//            }
//        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        }
    )
}

@Composable
fun FloatingAddButton() {
    ExtendedFloatingActionButton(
        onClick = {
            Timber.d("Add Floating Action Button CLICKED ++++++")
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
        AppMainScreen()
    }
}