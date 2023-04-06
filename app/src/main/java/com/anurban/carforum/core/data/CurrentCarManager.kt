package com.anurban.carforum.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.anurban.carforum.core.data.database.entity.Car

class CurrentCarManager() {

    private val mutableState = MediatorLiveData<Car>().apply {
        value = null
    }

    val state: LiveData<Car> = mutableState

    fun setCurrentCar(car: Car) {
        mutableState.value = car
    }
}