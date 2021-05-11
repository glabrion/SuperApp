package ru.sulatskov.superapp.di.module

import dagger.Module
import dagger.Provides
import ru.sulatskov.superapp.main.router.Router

@Module
class RouterModule() {

    @Provides
    fun provideRouter(): Router {
        return Router()
    }
}