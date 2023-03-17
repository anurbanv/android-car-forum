package com.anurban.carforum.app.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val mutableState = MediatorLiveData<HomeScreenState>()
    val state: LiveData<HomeScreenState> = mutableState

    fun onLicencePlateInputChange(value: String) {
        mutableState.value = mutableState.value?.copy(licencePlateInput = value)
    }
}
