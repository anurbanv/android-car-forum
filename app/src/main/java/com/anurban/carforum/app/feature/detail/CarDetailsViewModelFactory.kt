package com.anurban.carforum.app.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CarDetailsViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CarDetailsViewModelFactory() as T
        }
        throw IllegalAccessException("Unable to construct ViewModel")
    }
}
