package com.tuwaiq.todo_asmaa.Repo

import android.content.Context
import androidx.room.Query
import com.tuwaiq.todo_asmaa.data.ToDo_DataBase
import com.tuwaiq.todo_asmaa.model.Tag
import com.tuwaiq.todo_asmaa.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ToDo_Repo(val context :Context) {
    private val toDo_DB = ToDo_DataBase.getAppDataBase(context)!!

    suspend fun getAllTasks(): List<Task> = withContext(Dispatchers.IO)
    { toDo_DB.ToDoDao.getAllTasks() }

    suspend fun insertTask(task: Task) = withContext(Dispatchers.Main) {
        toDo_DB.ToDoDao.insertTask(task)
    }

    suspend fun insertTag(tag: Tag) = withContext(Dispatchers.IO) {
        toDo_DB.ToDoDao.insertTag(tag)
    }

    suspend fun updateTask(task: Task)= withContext(Dispatchers.IO){
        toDo_DB.ToDoDao.updateTask(task)
    }
    suspend fun deleteTask(task: Task)= withContext(Dispatchers.IO){
        toDo_DB.ToDoDao.deleteTask(task)
    }
    suspend fun deleteTaskById(id: Int)= withContext(Dispatchers.IO){
        toDo_DB.ToDoDao.deleteTaskById(id)
    }

    suspend fun updatethestate(state1:Boolean,taskId:String)= withContext(Dispatchers.IO){
        toDo_DB.ToDoDao.updatethestate(state1,taskId)
    }

    suspend  fun getAllIncompleteavailable(state1:Boolean,currentDate:String)= withContext(Dispatchers.IO){
        toDo_DB.ToDoDao.getAllIncompleteavailable(state1,currentDate)
    }

    suspend  fun getAlldoneTask(state1:Boolean)= withContext(Dispatchers.IO){
        toDo_DB.ToDoDao.getAlldoneTask(state1)
    }
    suspend  fun getAlloutdatedAndincompelte(state1:Boolean,currentDate:String)= withContext(Dispatchers.IO){
        toDo_DB.ToDoDao.getAlloutdatedAndincompelte(state1,currentDate)
    }


}