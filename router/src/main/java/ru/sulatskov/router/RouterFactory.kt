package ru.sulatskov.router

import androidx.appcompat.app.AppCompatActivity

class RouterFactory {

    fun create(activity: AppCompatActivity): Router {
        return PhoneRouter(activity)
    }
}
