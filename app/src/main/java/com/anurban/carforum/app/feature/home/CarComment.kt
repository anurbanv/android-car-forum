package com.anurban.carforum.app.feature.home

import com.anurban.carforum.core.data.database.entity.Car
import com.anurban.carforum.core.data.database.entity.Comment

data class CarComment(
    val car: Car,
    val comment: Comment
)