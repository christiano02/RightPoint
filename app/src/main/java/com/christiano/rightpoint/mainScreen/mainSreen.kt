package com.christiano.rightpoint.mainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.christiano.rightpoint.data.CheckInEntity
import com.christiano.rightpoint.viewModel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {

    val checkIns by viewModel
        .allCheckIns
        .collectAsState(initial = emptyList())

    MainScreenContent(
        checkIns = checkIns,
        onCheckInClick = { type -> viewModel.addCheckIn(type) }
    )
}

@Composable
fun MainScreenContent(
    checkIns: List<CheckInEntity>, // Substitua 'CheckInItem' pelo nome real da sua classe de dados
    onCheckInClick: (String) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Right Point", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { onCheckInClick("Entry") }) {
                Text("In")
            }
            Button(
                onClick = { onCheckInClick("Exit") },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Out")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn {
            items(checkIns) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = item.type, style = MaterialTheme.typography.titleMedium)
                            Text(text = item.date, style = MaterialTheme.typography.bodySmall)
                        }
                        Text(text = item.time, style = MaterialTheme.typography.headlineSmall)
                    }
                }
            }
        }
    }
}

// 3. O Preview renderiza apenas a versão Stateless com dados falsos (Mocks)
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    // Dados fictícios apenas para visualização no Android Studio
    val mockData = listOf(
        CheckInEntity(type = "Entry", date = "21/09/2026", time = "08:00"),
        CheckInEntity(type = "Exit", date = "21/09/2026", time = "12:00"),
        CheckInEntity(type = "Entry", date = "21/09/2026", time = "13:00"),
        CheckInEntity(type = "Exit", date = "21/09/2026", time = "17:00")
    )

    MaterialTheme {
        MainScreenContent(
            checkIns = mockData,
            onCheckInClick = { /* Ação vazia no preview */ }
        )
    }
}