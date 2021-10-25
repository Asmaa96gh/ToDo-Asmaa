package com.tuwaiq.todo_asmaa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.*


@Entity
data class Task(@PrimaryKey val id:Int,
                val title :String,
                val description:String,
                val creationDateTime: LocalDateTime,
                val DueDateTime: LocalDateTime,
                val state:String ="active",
                val place :String ="home",
                val tagId:Int =0)