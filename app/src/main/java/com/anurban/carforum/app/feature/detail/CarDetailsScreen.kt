@file:OptIn(ExperimentalMaterial3Api::class)

package com.anurban.carforum.app.feature.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.anurban.carforum.app.feature.detail.CarDetailsScreenEvent.CommentInputChanged
import com.anurban.carforum.app.feature.detail.CarDetailsScreenEvent.DislikeCar
import com.anurban.carforum.app.feature.detail.CarDetailsScreenEvent.LikeCar
import com.anurban.carforum.app.feature.detail.CarDetailsScreenEvent.PostComment
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun CarDetailsScreen() {

    CarDetailsScreenUi(
        state = CarDetailsScreenState(),
        eventListener = {
            when (it) {
                is CommentInputChanged -> TODO()
                DislikeCar -> TODO()
                LikeCar -> TODO()
                PostComment -> TODO()
            }
        }
    )
}

@Composable
private fun CarDetailsScreenUi(
    state: CarDetailsScreenState,
    eventListener: (CarDetailsScreenEvent) -> Unit = {},
) {
    Column {
        Text(text = "ABC 123")
        Button(onClick = { eventListener(LikeCar) }) {
            Text("Like")
        }
        Button(onClick = { eventListener(DislikeCar) }) {
            Text(text = "Dislike")
        }
        TextField(
            value = state.commentInput,
            onValueChange = { eventListener(CommentInputChanged(it)) },
        )
        Button(onClick = { eventListener(PostComment) }) {
            Text("Add comment")
        }
        Text(text = "(x comments)")

        // list of comments
    }
}

@Preview
@Composable
private fun CarDetailsScreenPreview() {
    CarDetailsScreenUi(
        state = CarDetailsScreenState()
    )
}
