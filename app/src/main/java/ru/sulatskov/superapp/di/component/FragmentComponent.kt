package ru.sulatskov.superapp.di.component

import dagger.Component
import ru.sulatskov.superapp.di.module.FragmentModule
import ru.sulatskov.superapp.main.screens.general.GeneralFragment
import ru.sulatskov.superapp.main.screens.service_screen.ServiceFragment

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(serviceFragment: ServiceFragment)

    fun inject(generalFragment: GeneralFragment)

}