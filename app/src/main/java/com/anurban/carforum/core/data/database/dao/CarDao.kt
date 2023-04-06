package com.anurban.carforum.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anurban.carforum.core.data.database.entity.Car

@Dao
interface CarDao {

    @Insert
    suspend fun insert(car: Car)

    @Query("SELECT * FROM car WHERE licensePlate = :licencePlate")
    suspend fun getByLicencePlate(licencePlate: String): Car?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(car: Car)
}
