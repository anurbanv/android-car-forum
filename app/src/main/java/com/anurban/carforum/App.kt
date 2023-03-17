package com.anurban.carforum

import android.app.Application
import androidx.room.Room
import com.anurban.carforum.core.data.database.AppDatabase

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()

        instance = this

        appDatabase = Room.databaseBuilder(
            applicationContext, AppDatabase::class.java, "app-db"
        ).build()
    }
}
