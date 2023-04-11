package com.anurban.carforum

import androidx.room.Room
import com.anurban.carforum.app.feature.detail.CarDetailsViewModel
import com.anurban.carforum.app.feature.home.HomeViewModel
import com.anurban.carforum.core.data.CurrentCarManager
import com.anurban.carforum.core.data.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(), AppDatabase::class.java, "app-db"
        ).build()
    }
    single { CurrentCarManager() }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { CarDetailsViewModel(get(), get()) }
}