package com.anurban.carforum.app.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.anurban.carforum.core.data.CurrentCarManager
import com.anurban.carforum.core.data.database.entity.Car
import com.anurban.carforum.core.data.database.entity.Comment
import com.anurban.carforum.updateState

class CarDetailsViewModel(
    currentCarManager: CurrentCarManager,
) : ViewModel() {

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
    }

    fun onCommentInputChange(value: String) {
        mutableState.updateState {
            copy(commentInput = value)
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