package com.tuwaiq.todo_asmaa.data
import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.util.Date
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

// UNUSED CLASS
object LocalDateTimeConverter {
    @TypeConverter
    fun toDate(dateString: String?): LocalDateTime? {
        return if (dateString == null) {
            null
        } else {
            LocalDateTime.parse(dateString)
        }
    }

    @TypeConverter
    fun toDateString(date: LocalDateTime?): String? {
        return if (date == null) {
            null
        } else {
            date.toString()
        }
    }
}


