package com.christiano.rightpoint.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christiano.rightpoint.data.CheckInDao
import com.christiano.rightpoint.data.CheckInEntity
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainViewModel(private val dao: CheckInDao) : ViewModel() {

    val allCheckIns = dao.listAllTheCheckIns()

    fun addCheckIn(type: String) {
        viewModelScope.launch {
            val now = LocalDateTime.now()
            val date = now.format(DateTimeFormatter.ofPattern("dd/MM/yy"))
            val time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"))

            val newEntry = CheckInEntity(
                date = date,
                time = time,
                type = type
            )
            dao.insertCheckIn(newEntry)

        }
    }
}