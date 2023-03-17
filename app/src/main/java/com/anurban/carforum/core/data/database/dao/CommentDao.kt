package com.anurban.carforum.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.anurban.carforum.core.data.database.entity.Comment

@Dao
interface CommentDao {

    @Insert
    suspend fun insert(car: Comment)
}
