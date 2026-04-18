package com.christiano.rightpoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.christiano.rightpoint.data.AppDataBase
import com.christiano.rightpoint.ui.theme.RightPointTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "rightpoint-db"
        ).build()

        val viewModel = MainViewModel(db.CheckInDao())

        setContent {
            RightPointTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val checkIns by viewModel.allCheckIns.collectAsState(initial = emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Right Point", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { viewModel.addCheckIn("Entry") }) {
                Text("In")
            }
            Button(onClick = { viewModel.addCheckIn("Exit") }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                Text("Out")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn {
            items(checkIns) { item ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
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