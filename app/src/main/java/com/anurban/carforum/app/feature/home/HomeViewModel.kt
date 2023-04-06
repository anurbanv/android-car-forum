package com.anurban.carforum.app.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.anurban.carforum.core.data.database.entity.Comment

class HomeViewModel : ViewModel() {

    private val mutableState = MediatorLiveData<HomeScreenState>()
    val state: LiveData<HomeScreenState> = mutableState

    fun onLicencePlateInputChange(value: String) {
        mutableState.value = mutableState.value?.copy(licencePlateInput = value)
    }

    fun onSearchClick() {
        // navigate to car
    }
}

data class HomeScreenState(
    val licencePlateInput: String = "",
    val latestComments: List<Comment> = listOf(),
)

sealed class HomeScreenEvent {
    data class LicencePlateInput(val value: String) : HomeScreenEvent()
    object SearchCarLicencePlate : HomeScreenEvent()
}