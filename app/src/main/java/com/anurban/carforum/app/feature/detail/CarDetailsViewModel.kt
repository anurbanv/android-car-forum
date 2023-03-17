package com.anurban.carforum.app.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

class CarDetailsViewModel : ViewModel() {

    private val mutableState = MediatorLiveData<CarDetailsScreenState>()

    val state: LiveData<CarDetailsScreenState> = mutableState

}
