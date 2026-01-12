package com.example.notesandtodo.screens

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.notesandtodo.viewmodel.ToDoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoScreen(
    viewModel: ToDoViewModel,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableIntStateOf(1) }
    var dueDate by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Task") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (title.isNotBlank()) {
                        viewModel.addTodo(
                            title = title,
                            isDone = true,
                            priority = selectedPriority,
                            dueDate = if (dueDate.isNotEmpty()) dueDate.toLong() else null
                        )
                        onBack()
                    } else {
                        Toast.makeText(context, "Task name required!", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Icon(Icons.Default.Check, contentDescription = "Save")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            // Title
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Task Title") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Priority Selector
            Text("Priority", style = MaterialTheme.typography.titleMedium)

            PrioritySelector(
                selectedPriority = selectedPriority,
                onPrioritySelected = { selectedPriority = it }
            )

            // Due Date (optional)
            OutlinedTextField(
                value = dueDate,
                onValueChange = { dueDate = it },
                label = { Text("Due Date (optional)") },
                placeholder = { Text("DD/MM/YYYY") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun PrioritySelector(selectedPriority: Int, onPrioritySelected: (Int) -> Unit) {

    val priorities = listOf("Low", "Medium", "High")
    val colors = listOf(Color.Green, Color.Yellow, Color.Red)

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        priorities.forEachIndexed { index, label ->

            val isSelected = selectedPriority == index + 1

            val scale by animateFloatAsState(if (isSelected) 1.15f else 1f)

            AssistChip(
                onClick = { onPrioritySelected(index + 1) },
                label = { Text(label) },
                leadingIcon = {
                    Box(
                        Modifier
                            .size(12.dp)
                            .background(colors[index], CircleShape)
                    )
                },
                modifier = Modifier.scale(scale),
                border = if (isSelected) {
                    BorderStroke(1.dp, colors[index])
                } else {
                    BorderStroke(0.dp, colors[index])
                }
            )
        }
    }
}
