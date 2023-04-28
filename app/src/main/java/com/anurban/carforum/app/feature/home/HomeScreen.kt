@file:OptIn(ExperimentalMaterial3Api::class)

package com.anurban.carforum.app.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anurban.carforum.app.feature.home.HomeScreenEvent.LicencePlateInput
import com.anurban.carforum.app.feature.home.HomeScreenEvent.SearchCarLicencePlate
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@RootNavGraph(start = true)
@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
) {
    val viewModel: HomeViewModel = koinViewModel()

    val state = viewModel.state.observeAsState().value ?: return

    HomeScreenUi(
        state = state,
        eventListener = { event ->
            with(event) {
                when (this) {
                    is LicencePlateInput -> viewModel.onLicencePlateInputChange(value)
                    SearchCarLicencePlate -> viewModel.onSearchClick(navigator)
                }
            }
        }
    )
}

@Composable
private fun HomeScreenUi(
    state: HomeScreenState,
    eventListener: (HomeScreenEvent) -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Welcome")
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = state.licencePlateInput,
            onValueChange = { eventListener(LicencePlateInput(it)) },
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { eventListener(SearchCarLicencePlate) }) {
            Text(text = "Go")
        }
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Latest Comments")
        Spacer(modifier = Modifier.height(8.dp))
        state.latestComments.forEach {
            Text(text = "${it.car.licensePlate} \t\t ${it.comment.text}")
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreenUi(
        state = HomeScreenState(),
    )
}
