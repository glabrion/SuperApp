package ru.sulatskov.hiltexample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ru.sulatskov.hiltexample.main.screens.general.GeneralPresenter
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MainModule {
    @Singleton
    @Provides
    fun provideGeneralPresenter() = GeneralPresenter()
}