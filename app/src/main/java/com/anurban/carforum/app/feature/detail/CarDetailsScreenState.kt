package com.anurban.carforum.app.feature.detail

import com.anurban.carforum.core.data.database.entity.Comment

data class CarDetailsScreenState(
    val licencePlate: String = "",
    val numOfLikes: Long = 0,
    val numOfDislikes: Long = 0,
    val commentInput: String = "",
    val comments: List<Comment> = listOf(),
)