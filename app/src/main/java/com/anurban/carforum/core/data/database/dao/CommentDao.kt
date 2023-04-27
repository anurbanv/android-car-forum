package com.anurban.carforum.core.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anurban.carforum.core.data.database.entity.Comment

@Dao
interface CommentDao {

    @Insert
    suspend fun insert(car: Comment)

    @Query("SELECT * FROM comment WHERE carId = :carId")
    fun getAllByCarId(carId: Long): LiveData<List<Comment>>

    @Query("SELECT * FROM comment ORDER BY id DESC LIMIT 5")
    fun getLatestComments(): LiveData<List<Comment>>
}
