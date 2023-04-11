package com.anurban.carforum

import androidx.lifecycle.MediatorLiveData

inline fun <reified T> MediatorLiveData<T>.updateState(update: T.() -> T) {
    this.value = update(this.value ?: (Class.forName(T::class.qualifiedName!!).newInstance() as T))
}