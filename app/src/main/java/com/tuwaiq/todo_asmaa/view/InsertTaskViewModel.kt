package com.tuwaiq.todo_asmaa.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tuwaiq.todo_asmaa.Repo.ToDo_Repo
import com.tuwaiq.todo_asmaa.model.Task
import kotlinx.coroutines.launch

class InsertTaskViewModel(context: Application) : AndroidViewModel(context) {

    private val mainRepo = ToDo_Repo(context)

    fun addTask(task: Task){
        viewModelScope.launch { mainRepo.insertTask(task)}
    }

}