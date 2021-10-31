package com.tuwaiq.todo_asmaa.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class Tag (@PrimaryKey val id:Int, val name:String):Parcelable

