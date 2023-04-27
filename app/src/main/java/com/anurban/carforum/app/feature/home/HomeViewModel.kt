package com.anurban.carforum.app.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anurban.carforum.app.feature.destinations.CarDetailsScreenDestination
import com.anurban.carforum.core.data.CurrentCarManager
import com.anurban.carforum.core.data.database.AppDatabase
import com.anurban.carforum.core.data.database.entity.Car
import com.anurban.carforum.core.data.database.entity.Comment
import com.anurban.carforum.updateState
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    appDatabase: AppDatabase,
    private val currentCarManager: CurrentCarManager,
) : ViewModel() {

    private val carDao = appDatabase.carDao()
    private val commentDao = appDatabase.commentDao()

    private val mutableState = MediatorLiveData<HomeScreenState>().apply {
        value = HomeScreenState()
    }

    val state: LiveData<HomeScreenState> = mutableState

    init {
        mutableState.addSource(commentDao.getLatestComments()) { comments ->
            viewModelScope.launch {
                val carComments = mutableListOf<CarComment>()
                comments.forEach {
                    carDao.getByCarId(it.carId)?.let { car ->
                        carComments.add(CarComment(car = car, comment = it))
                    }
                }
                mutableState.updateState { copy(latestComments = carComments) }
            }
        }
    }

    fun onLicencePlateInputChange(value: String) {
        mutableState.updateState {
            copy(licencePlateInput = value)
        }
    }

    fun onSearchClick(navigator: DestinationsNavigator) {
        val licencePlateInput = mutableState.value?.licencePlateInput

        if (licencePlateInput.isNullOrEmpty()) return

        viewModelScope.launch {
            var car = carDao.getByLicencePlate(licencePlateInput)
            if (car == null) {
                car = Car(licensePlate = licencePlateInput)
                val carId = carDao.insert(car)
                car.id = carId
            }
            currentCarManager.setCurrentCar(car)
            withContext(Main) {
                navigator.navigate(CarDetailsScreenDestination)
            }
        }
    }
}

data class HomeScreenState(
    val licencePlateInput: String = "",
    val latestComments: List<CarComment> = listOf(),
)

sealed class HomeScreenEvent {
    data class LicencePlateInput(val value: String) : HomeScreenEvent()
    object SearchCarLicencePlate : HomeScreenEvent()
}