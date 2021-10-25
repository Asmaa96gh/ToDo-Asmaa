package com.tuwaiq.todo_asmaa.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.tuwaiq.todo_asmaa.model.Tag
import com.tuwaiq.todo_asmaa.model.Task
import com.tuwaiq.todo_asmaa.model.TaskToTag


data class TaskTag_Pair  (
 @Embedded
val task: Task,
 @Relation(
 parentColumn = "id",
 entity = Tag::class,
 entityColumn = "id",
 associateBy = Junction(
  value = TaskToTag::class,
  parentColumn = "TaskId",
  entityColumn = "TagId"
 )
)
val tags :List<Tag>

)


