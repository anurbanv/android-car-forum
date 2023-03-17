package com.anurban.carforum.app.feature.home

import com.anurban.carforum.core.data.database.entity.Comment

data class HomeScreenState(
    val licencePlateInput: String = "",
    val latestComments: List<Comment> = listOf(),
)
