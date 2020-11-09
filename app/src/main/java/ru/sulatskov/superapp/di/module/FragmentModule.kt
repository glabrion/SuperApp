package ru.sulatskov.superapp.di.module

import dagger.Module
import dagger.Provides
import ru.sulatskov.superapp.main.screens.general.GeneralPresenter
import ru.sulatskov.superapp.main.screens.service_screen.ServicePresenter

@Module
class FragmentModule {

    @Provides
    fun provideServicePresenter(): ServicePresenter {
        return ServicePresenter()
    }

    @Provides
    fun provideGeneralPresenter(): GeneralPresenter {
        return GeneralPresenter()
    }


}
