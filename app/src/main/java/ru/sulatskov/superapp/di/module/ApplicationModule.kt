package ru.sulatskov.superapp.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.sulatskov.superapp.BaseApp
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return baseApp
    }
}