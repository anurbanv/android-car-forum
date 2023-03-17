package com.anurban.carforum.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anurban.carforum.core.data.database.dao.CarDao
import com.anurban.carforum.core.data.database.dao.CommentDao
import com.anurban.carforum.core.data.database.entity.Car
import com.anurban.carforum.core.data.database.entity.Comment

@Database(entities = [Car::class, Comment::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    abstract fun commentDao(): CommentDao
}
