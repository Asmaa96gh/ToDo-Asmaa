package com.tuwaiq.todo_asmaa.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.todo_asmaa.Repo.ToDo_Repo
import com.tuwaiq.todo_asmaa.model.Task
import kotlinx.coroutines.launch

class MainViewModel(context: Application) : AndroidViewModel(context){
    private val mainRepo =ToDo_Repo(context)

    fun getAllTasks(): MutableLiveData<List<Task>> {
        val tasks = MutableLiveData<List<Task>>() //
        viewModelScope.launch { tasks.postValue(mainRepo.getAllTasks())}
        // postValue() is for update the value in the main data
        return tasks
    }
    fun addTask(task:Task){
        viewModelScope.launch { mainRepo.insertTask(task)}
    }

}