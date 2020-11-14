package ru.sulatskov.superapp

import android.app.Application
import ru.sulatskov.superapp.di.component.DaggerMainComponent

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerMainComponent.builder().build().inject(this)
    }
}