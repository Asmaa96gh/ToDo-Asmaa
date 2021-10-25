package com.tuwaiq.todo_asmaa.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys=["tagId" ,"taskId"])
data class TaskToTag(val tagId :Int ,val taskId:Int)