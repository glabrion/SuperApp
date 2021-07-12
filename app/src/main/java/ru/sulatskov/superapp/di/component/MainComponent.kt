package ru.sulatskov.superapp.di.component

import dagger.Component
import ru.sulatskov.superapp.BaseApp
import ru.sulatskov.superapp.di.module.ApplicationModule
import ru.sulatskov.superapp.di.module.FragmentModule
import ru.sulatskov.superapp.main.screens.app_bar_screen.AppBarFragment
import ru.sulatskov.superapp.main.screens.button_screen.ButtonFragment
import ru.sulatskov.superapp.main.screens.content_provider_screen.ContactFragment
import ru.sulatskov.superapp.main.screens.edit_text_screen.EditTextFragment
import ru.sulatskov.superapp.main.screens.general_screen.GeneralFragment
import ru.sulatskov.superapp.main.screens.image_view_screen.ImageViewFragment
import ru.sulatskov.superapp.main.screens.recycler_view.RecyclerViewFragment
import ru.sulatskov.superapp.main.screens.service_screen.ServiceFragment
import ru.sulatskov.superapp.main.screens.text_view_screen.TextViewFragment
import ru.sulatskov.superapp.main.screens.work_manager_screen.WorkManagerFragment

@Component(modules = [ApplicationModule::class, FragmentModule::class])
interface MainComponent {

    fun inject(application: BaseApp)

    fun inject(serviceFragment: ServiceFragment)
    fun inject(generalFragment: GeneralFragment)
    fun inject(contactFragment: ContactFragment)
    fun inject(textViewFragment: TextViewFragment)
    fun inject(editTextFragment: EditTextFragment)
    fun inject(buttonFragment: ButtonFragment)
    fun inject(appBarFragment: AppBarFragment)
    fun inject(imageViewFragment: ImageViewFragment)
    fun inject(workManagerFragment: WorkManagerFragment)
    fun inject(recyclerViewFragment: RecyclerViewFragment)
}