package ru.sulatskov.superapp.di.component

import dagger.Component
import ru.sulatskov.superapp.di.module.ContextModule
import ru.sulatskov.superapp.di.module.FragmentModule
import ru.sulatskov.superapp.di.module.RouterModule
import ru.sulatskov.superapp.main.MainActivity
import ru.sulatskov.superapp.main.router.Router
import ru.sulatskov.superapp.main.screens.contact.ContactFragment
import ru.sulatskov.superapp.main.screens.editText.EditTextFragment
import ru.sulatskov.superapp.main.screens.general.GeneralFragment
import ru.sulatskov.superapp.main.screens.service_screen.ServiceFragment
import ru.sulatskov.superapp.main.screens.textView.TextViewFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [FragmentModule::class, RouterModule::class, ContextModule::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(router: Router)
    fun inject(serviceFragment: ServiceFragment)
    fun inject(generalFragment: GeneralFragment)
    fun inject(contactFragment: ContactFragment)
    fun inject(textViewFragment: TextViewFragment)
    fun inject(editTextFragment: EditTextFragment)

}