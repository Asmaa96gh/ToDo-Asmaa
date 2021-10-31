package com.tuwaiq.todo_asmaa.data

import androidx.room.*
import com.tuwaiq.todo_asmaa.model.Tag
import com.tuwaiq.todo_asmaa.model.Task


@Dao
interface  ToDo_Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task) // suspend: means put it on different thread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
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

    @Query("delete from Task where id == :taskId")
    suspend fun deleteTaskById(taskId:Int)

    @Query("select * from Task where  id == :taskId")
    suspend fun selectTaskById(taskId :Int): Task

    @Query("SELECT * FROM Task ORDER BY DueDateTime ASC")
    suspend  fun getAllTasksByTimeDEC():List<Task>

    @Query("update Task set state = :state1 where id == :taskId")
    suspend  fun updatethestate(state1:Boolean,taskId:String)

    @Query("select * from Task where state =:state1 and :currentDate > DueDateTime ")
    suspend  fun getAlloutdatedAndincompelte(state1:Boolean,currentDate:String):List<Task>

    @Query("select * from Task where state =:state1 and :currentDate <= DueDateTime ")
    suspend  fun getAllIncompleteavailable(state1:Boolean,currentDate:String):List<Task>

    @Query("select * from Task where state =:state1 ")
    suspend  fun getAlldoneTask(state1:Boolean):List<Task>
}