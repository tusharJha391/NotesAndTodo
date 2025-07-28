package com.example.notesandtodo.bottommenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.CheckCircle
import androidx.compose.material.icons.sharp.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainBottomBar(onClick: (Int) -> Unit = {}) {
    val startDestination = Destination.NOTE
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
        Destination.entries.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = selectedDestination == index,
                onClick = {
                    selectedDestination = index
                    onClick.invoke(destination.ordinal)
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.contentDescription
                    )
                },
                label = { Text(text = destination.label) }
            )
        }
    }
}

enum class Destination(
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    NOTE("Note", Icons.Sharp.MailOutline, "Note list"),
    TODO("ToDo", Icons.Sharp.CheckCircle, "ToDo list")
}

@Preview
@Composable
private fun BottomMenu() {
    MainBottomBar()
}