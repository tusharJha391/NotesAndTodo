package com.example.notesandtodo.custom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.notesandtodo.database.models.NoteData


@Composable
fun NoteItem(note: NoteData, onClick: () -> Unit,onDelete: () -> Unit) {
    SwipeableCard(onDelete = onDelete) {
        ClickableCard(onClick = onClick, content = {
            Column(Modifier.padding(16.dp)) {
                Text(note.title, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(4.dp))
                Text(note.description, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
        })
    }
}