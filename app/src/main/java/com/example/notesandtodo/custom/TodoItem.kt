package com.example.notesandtodo.custom

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesandtodo.database.models.TodoData


@Composable
fun TodoItem(todo: TodoData, onToggle: () -> Unit, onDelete: () -> Unit) {
    SwipeableCard(onDelete = onDelete) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Checkbox(checked = todo.isDone, onCheckedChange = { onToggle() })
            Text(todo.title, Modifier.padding(start = 8.dp))
        }
    }
}