@file:OptIn(ExperimentalMaterial3Api::class)

package com.anurban.carforum.app.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.anurban.carforum.app.feature.home.HomeScreenEvent.LicencePlateInput
import com.anurban.carforum.app.feature.home.HomeScreenEvent.SearchCarLicencePlate
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination(start = true)
@Composable
fun HomeScreen() {
    HomeScreenUi(
        state = HomeScreenState(),
        eventListener = { event ->
            when (event) {
                is LicencePlateInput -> {}
                SearchCarLicencePlate -> {}
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
    ) {
        Text(text = "Welcome")

        TextField(
            value = state.licencePlateInput,
            onValueChange = { eventListener(LicencePlateInput(it)) },
        )

        Button(onClick = { eventListener(SearchCarLicencePlate) }) {
            Text(text = "Go")
        }

        Text(text = "Latest Comments")
        // list of comments
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreenUi(
        state = HomeScreenState(),
    )
}
