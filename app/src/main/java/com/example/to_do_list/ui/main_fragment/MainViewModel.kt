package com.example.to_do_list.ui.main_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.to_do_list.data.Task
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel : ViewModel() {
    private val listOfTasks = mutableListOf<Task>()
    val getCurrentData = MutableLiveData<String>()
    val listOfTask = MutableLiveData<MutableList<Task>>()
    val getTask = MutableLiveData<Task>()
    val countOfTasks = MutableLiveData(getCountOfTask())
    private var i = 1

    private fun getCountOfTask() = "${listOfTasks.size} Tasks"

    fun getListOfTasks() {
        listOfTask.value = listOfTasks
    }

    fun addTask() {
        getTask.value = Task("Task $i")
        countOfTasks.value = getCountOfTask()
        i++

    }

    // Create formatted date "day month"
    fun getDate() {
        val date = Date()
        val sdf = SimpleDateFormat("dd MMMM")
        val dateList = sdf.format(date).split(" ")
        val dateString = "${dateList[0]} ${dateList[1].capitalize()}"
        getCurrentData.value = dateString
    }
}