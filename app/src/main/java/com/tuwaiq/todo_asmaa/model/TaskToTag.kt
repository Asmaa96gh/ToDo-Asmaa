package com.tuwaiq.todo_asmaa.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(primaryKeys=["tagId" ,"taskId"])
@Parcelize
data class TaskToTag(val tagId :Int ,val taskId:Int):Parcelable
