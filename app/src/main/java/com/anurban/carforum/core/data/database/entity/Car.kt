package com.anurban.carforum.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car")
data class Car(
    @PrimaryKey val id: Int = 0,
    val licensePlate: String,
    val likes: Long,
    val dislikes: Long,
)
