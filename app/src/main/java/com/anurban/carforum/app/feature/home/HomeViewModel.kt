package com.anurban.carforum.app.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anurban.carforum.app.feature.destinations.CarDetailsScreenDestination
import com.anurban.carforum.core.data.database.AppDatabase
import com.anurban.carforum.core.data.database.entity.Car
import com.anurban.carforum.core.data.database.entity.Comment
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

class HomeViewModel(
    appDatabase: AppDatabase,
) : ViewModel() {

    private val carDao = appDatabase.carDao()

    private val mutableState = MediatorLiveData<HomeScreenState>().apply {
        value = HomeScreenState()
    }

    val state: LiveData<HomeScreenState> = mutableState

    fun onLicencePlateInputChange(value: String) {
        mutableState.value = mutableState.value?.copy(licencePlateInput = value)
    }

    fun onSearchClick(navigator: DestinationsNavigator) {
        val licencePlateInput = mutableState.value?.licencePlateInput

        if (licencePlateInput.isNullOrEmpty()) return

        viewModelScope.launch {
            var byLicencePlate = carDao.getByLicencePlate(licencePlateInput)
            if (byLicencePlate == null) {
                byLicencePlate = Car(licensePlate = licencePlateInput)
                carDao.insert(byLicencePlate)
            }
        }

        navigator.navigate(CarDetailsScreenDestination)
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