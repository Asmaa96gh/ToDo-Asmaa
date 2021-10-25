package com.tuwaiq.todo_asmaa.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity


data class Tag (@PrimaryKey val id:Int, val name:String)