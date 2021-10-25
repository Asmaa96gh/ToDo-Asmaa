package com.tuwaiq.todo_asmaa.data

import androidx.room.*
import com.tuwaiq.todo_asmaa.model.Tag
import com.tuwaiq.todo_asmaa.model.Task


@Dao
interface  ToDo_Dao {
    @Insert
    suspend fun insertTask(task: Task) // suspend: means put it on different thread
    @Insert
    suspend fun insertTag(tag: Tag)

    @Query("select * from Task")
    suspend  fun getAllTasks():List<Task>

    @Query("select * from Tag")
    suspend  fun getAllTags():List<Tag>
    @Update()
    suspend fun updateTask(task: Task)
    @Update()
    suspend fun updateTag(tag: Tag)
    @Delete
    suspend fun deleteTask(task: Task)
    @Delete
    suspend fun deleteTag(task: Task)

    @Query("delete from TaskToTag where taskId == :taskId")
    suspend fun deleteRelationWithTask(taskId:Int)

    @Query("delete from TaskToTag where taskId == :tagId")
    suspend fun deleteRelationWithTag(tagId:Int)


    @Query("select * from Task where  id == :taskId")
    suspend fun selectTaskById(taskId :Int): Task

    @Query("SELECT * FROM Task ORDER BY DueDateTime ASC")
    suspend  fun getAllTasksByTimeDEC():List<Task>
}