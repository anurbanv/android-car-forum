package com.anurban.carforum.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.anurban.carforum.core.data.database.entity.Car

@Dao
interface CarDao {

    @Insert
    suspend fun insert(car: Car)
}
