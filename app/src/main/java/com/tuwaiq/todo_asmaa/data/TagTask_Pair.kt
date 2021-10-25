package com.tuwaiq.todo_asmaa.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.tuwaiq.todo_asmaa.model.Tag
import com.tuwaiq.todo_asmaa.model.Task
import com.tuwaiq.todo_asmaa.model.TaskToTag

data class TagTask_Pair (
    @Embedded
    val tag: Tag,
    @Relation(
        parentColumn = "id",
        entity = Task::class,
        entityColumn = "id",
        associateBy = Junction(
            value = TaskToTag::class,
            parentColumn = "TagId",
            entityColumn = "TaskId"
        )
    )
    val Tasks :List<Task>

)
