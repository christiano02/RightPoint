package com.christiano.rightpoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.christiano.rightpoint.data.AppDataBase
import com.christiano.rightpoint.mainScreen.MainScreen
import com.christiano.rightpoint.ui.theme.RightPointTheme
import com.christiano.rightpoint.viewModel.MainViewModel


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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel)
                }
            }
        }
    }
}