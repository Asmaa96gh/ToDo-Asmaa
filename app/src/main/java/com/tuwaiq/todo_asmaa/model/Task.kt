package com.tuwaiq.todo_asmaa.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.*


@Entity
@Parcelize
data class Task(@PrimaryKey val id:String,
                val title :String,
                val description:String,
                val creationDateTime: String,
                val DueDateTime: String,
                val state:Boolean =false,
                val place :String ="unfilled",
                val tagId:Int =0):Parcelable