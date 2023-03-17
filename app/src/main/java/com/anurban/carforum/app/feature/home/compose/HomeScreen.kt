@file:OptIn(ExperimentalMaterial3Api::class)

package com.anurban.carforum.app.feature.home.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.anurban.carforum.app.feature.home.HomeScreenState
import com.anurban.carforum.app.feature.home.HomeViewModel
import com.anurban.carforum.app.feature.home.compose.HomeScreenEvent.LicencePlateInput

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
) {
    val state = homeViewModel.state.observeAsState().value ?: return

    HomeScreenUi(
        state = state,
        eventListener = { event ->
            when (event) {
                is LicencePlateInput -> homeViewModel.onLicencePlateInputChange(event.value)
            }
        }
    )
}

@Composable
private fun HomeScreenUi(
    state: HomeScreenState,
    eventListener: (HomeScreenEvent) -> Unit = {},
) {
    Column {
        Text(text = "Welcome")

        TextField(
            value = state.licencePlateInput,
            onValueChange = { eventListener(LicencePlateInput(it)) },
        )

        Text(text = "Latest Comments")
    }
}

sealed class HomeScreenEvent {
    data class LicencePlateInput(val value: String) : HomeScreenEvent()
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreenUi(
        state = HomeScreenState(),
    )
}
