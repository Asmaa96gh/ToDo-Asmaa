package com.tuwaiq.todo_asmaa.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tuwaiq.todo_asmaa.model.Tag
import com.tuwaiq.todo_asmaa.model.Task
import com.tuwaiq.todo_asmaa.model.TaskToTag


@Database(entities = [Tag::class , Task::class , TaskToTag::class], version = 1 ,exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class)
abstract class ToDo_DataBase :RoomDatabase(){
  abstract val ToDoDao: ToDo_Dao

    companion object{
        private var INSTANCE :ToDo_DataBase?= null
        fun getAppDataBase(context: Context): ToDo_DataBase? {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ToDo_DataBase::class.java,
                    "App-database"

                ).build()
            }
            return INSTANCE
        }
    }
}