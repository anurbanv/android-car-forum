@file:OptIn(ExperimentalMaterial3Api::class)

package com.anurban.carforum.app.feature.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                LikeCar -> viewModel.addLikeAction()
                DislikeCar -> viewModel.removeLikeAction()
                PostComment -> viewModel.onPostCommentAction()
                GoBack -> viewModel.onGoBackAction(navigator)
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

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { eventListener(GoBack) }) {
            Text(text = "Go back")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "License plate: ${car.licensePlate}")
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Button(onClick = { eventListener(LikeCar) }) {
                Text("Like ${car.likes}")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { eventListener(DislikeCar) }) {
                Text(text = "Dislike ${car.dislikes}")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = state.commentInput,
            onValueChange = { eventListener(CommentInputChanged(it)) },
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { eventListener(PostComment) }) {
            Text("Add comment")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "(${state.comments.size} comments)")

        LazyColumn {
            items(state.comments) {
                Text(text = it.text)
            }
        }
    }
}

@Preview
@Composable
private fun CarDetailsScreenPreview() {
    CarDetailsScreenUi(
        state = CarDetailsScreenState()
    )
}
