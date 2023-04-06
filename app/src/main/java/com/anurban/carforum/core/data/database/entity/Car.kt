package com.anurban.carforum.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car")
data class Car(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    val licensePlate: String,
    val likes: Long = 0,
    val dislikes: Long = 0,
)
