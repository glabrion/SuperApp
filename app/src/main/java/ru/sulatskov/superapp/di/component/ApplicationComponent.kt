package ru.sulatskov.superapp.di.component

import dagger.Component
import ru.sulatskov.superapp.BaseApp
import ru.sulatskov.superapp.di.module.ApplicationModule

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)

}