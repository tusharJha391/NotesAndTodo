package com.example.notesandtodo.screens

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.notesandtodo.viewmodel.NoteViewModel
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppMainScreen(modifier: Modifier = Modifier, noteViewModel: NoteViewModel? = null) {
    LaunchedEffect(key1 = Unit) {
        noteViewModel?.noteLiveData?.collectLatest {
            Timber.d("note data = ${it.size}")
        }
    }
    Scaffold(modifier = modifier,
        topBar = { MainTopAppbar() },
        bottomBar = { MainBottomBar() }
        ) { innerPadding ->

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
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = ""
                )
            }
        },
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
fun MainBottomBar() {
//    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
//        NavigationBarItem()
//    }
}