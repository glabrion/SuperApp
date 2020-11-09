package ru.sulatskov.superapp

import android.app.Application
import ru.sulatskov.superapp.di.component.DaggerApplicationComponent

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder().build().inject(this)
    }
}