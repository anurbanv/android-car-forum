package com.anurban.carforum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.anurban.carforum.app.feature.NavGraphs
import com.anurban.carforum.ui.theme.CarForumTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarForumTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
