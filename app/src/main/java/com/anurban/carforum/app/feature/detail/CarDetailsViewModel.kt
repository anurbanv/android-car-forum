package com.anurban.carforum.app.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.anurban.carforum.core.data.database.entity.Comment

class CarDetailsViewModel : ViewModel() {

    private val mutableState = MediatorLiveData<CarDetailsScreenState>()

    val state: LiveData<CarDetailsScreenState> = mutableState
}

data class CarDetailsScreenState(
    val licencePlate: String = "",
    val numOfLikes: Long = 0,
    val numOfDislikes: Long = 0,
    val commentInput: String = "",
    val comments: List<Comment> = listOf(),
)

sealed interface CarDetailsScreenEvent {
    object GoBack: CarDetailsScreenEvent
    object LikeCar : CarDetailsScreenEvent
    object DislikeCar : CarDetailsScreenEvent
    data class CommentInputChanged(val value: String) : CarDetailsScreenEvent
    object PostComment : CarDetailsScreenEvent
}