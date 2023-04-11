@file:OptIn(ExperimentalMaterial3Api::class)

package com.anurban.carforum.app.feature.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.anurban.carforum.app.feature.detail.CarDetailsScreenEvent.CommentInputChanged
import com.anurban.carforum.app.feature.detail.CarDetailsScreenEvent.DislikeCar
import com.anurban.carforum.app.feature.detail.CarDetailsScreenEvent.GoBack
import com.anurban.carforum.app.feature.detail.CarDetailsScreenEvent.LikeCar
import com.anurban.carforum.app.feature.detail.CarDetailsScreenEvent.PostComment
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun CarDetailsScreen(
    navigator: DestinationsNavigator,
) {
    val viewModel: CarDetailsViewModel = koinViewModel()

    val state = viewModel.state.observeAsState().value ?: return

    CarDetailsScreenUi(
        state = state,
        eventListener = { event ->
            when (event) {
                is CommentInputChanged -> viewModel.onCommentInputChange(event.value)
                DislikeCar -> {}
                LikeCar -> {}
                PostComment -> {}
                GoBack -> navigator.popBackStack()
            }
        }
    )
}

@Composable
private fun CarDetailsScreenUi(
    state: CarDetailsScreenState,
    eventListener: (CarDetailsScreenEvent) -> Unit = {},
) {

    val car = state.car ?: return

    Column {
        Button(onClick = { eventListener(GoBack) }) {
            Text(text = "Go back")
        }
        Text(text = car.licensePlate)
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
