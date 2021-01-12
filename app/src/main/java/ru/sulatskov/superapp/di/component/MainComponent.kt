package ru.sulatskov.superapp.di.component

import dagger.Component
import ru.sulatskov.superapp.BaseApp
import ru.sulatskov.superapp.di.module.ApplicationModule
import ru.sulatskov.superapp.di.module.FragmentModule
import ru.sulatskov.superapp.main.screens.contact.ContactFragment
import ru.sulatskov.superapp.main.screens.editText.EditTextFragment
import ru.sulatskov.superapp.main.screens.general.GeneralFragment
import ru.sulatskov.superapp.main.screens.service_screen.ServiceFragment
import ru.sulatskov.superapp.main.screens.textView.TextViewFragment

@Component(modules = [ApplicationModule::class, FragmentModule::class])
interface MainComponent {

    fun inject(application: BaseApp)

    fun inject(serviceFragment: ServiceFragment)
    fun inject(generalFragment: GeneralFragment)
    fun inject(contactFragment: ContactFragment)
    fun inject(textViewFragment: TextViewFragment)
    fun inject(editTextFragment: EditTextFragment)

}