package com.anurban.carforum.app.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anurban.carforum.core.data.CurrentCarManager
import com.anurban.carforum.core.data.database.AppDatabase
import com.anurban.carforum.core.data.database.entity.Car
import com.anurban.carforum.core.data.database.entity.Comment
import com.anurban.carforum.updateState
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CarDetailsViewModel(
    private val currentCarManager: CurrentCarManager,
    appDatabase: AppDatabase,
) : ViewModel() {

    private val commentDao = appDatabase.commentDao()

    private val carDao = appDatabase.carDao()

    private val mutableState = MediatorLiveData<CarDetailsScreenState>().apply {
        value = CarDetailsScreenState()
    }

    val state: LiveData<CarDetailsScreenState> = mutableState

    init {
        mutableState.addSource(currentCarManager.state) {
            mutableState.updateState {
                copy(car = it)
            }
        }
        currentCarManager.state.value?.let { car ->
            mutableState.addSource(commentDao.getAllByCarId(car.id)) {
                mutableState.updateState {
                    copy(comments = it)
                }
            }
        }
    }

    fun onGoBackAction(navigator: DestinationsNavigator) {
        mutableState.updateState { CarDetailsScreenState() }
        navigator.popBackStack()
    }

    fun onCommentInputChange(value: String) {
        mutableState.updateState {
            copy(commentInput = value)
        }
    }

    fun onPostCommentAction() {
        val car = mutableState.value?.car ?: return
        val commentInput = mutableState.value?.commentInput ?: return

        viewModelScope.launch(Default) {
            commentDao.insert(
                Comment(
                    text = commentInput,
                    carId = car.id,
                )
            )

            withContext(Main) {
                mutableState.updateState {
                    copy(commentInput = "")
                }
            }
        }
    }

    fun addLikeAction() {
        viewModelScope.launch {
            var car = currentCarManager.state.value ?: return@launch
            car = car.copy(
                likes = (car.likes + 1)
            )
            currentCarManager.setCurrentCar(car)
            carDao.upsert(car)
        }
    }

    fun removeLikeAction() {
        viewModelScope.launch {
            var car = currentCarManager.state.value ?: return@launch
            car = car.copy(
                dislikes = (car.dislikes + 1)
            )
            currentCarManager.setCurrentCar(car)
            carDao.upsert(car)
        }
    }
}

data class CarDetailsScreenState(
    val car: Car? = null,
    val commentInput: String = "",
    val comments: List<Comment> = listOf(),
)

sealed interface CarDetailsScreenEvent {
    object GoBack : CarDetailsScreenEvent
    object LikeCar : CarDetailsScreenEvent
    object DislikeCar : CarDetailsScreenEvent
    data class CommentInputChanged(val value: String) : CarDetailsScreenEvent
    object PostComment : CarDetailsScreenEvent
}