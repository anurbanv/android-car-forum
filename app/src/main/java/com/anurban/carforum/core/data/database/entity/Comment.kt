package com.anurban.carforum.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment")
data class Comment(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    val text: String,
    val carId: Long,
)
