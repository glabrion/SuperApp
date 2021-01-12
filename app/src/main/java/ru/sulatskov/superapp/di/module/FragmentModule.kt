package ru.sulatskov.superapp.di.module

import dagger.Module
import dagger.Provides
import ru.sulatskov.superapp.main.screens.contact.ContactPresenter
import ru.sulatskov.superapp.main.screens.editText.EditTextPresenter
import ru.sulatskov.superapp.main.screens.general.GeneralPresenter
import ru.sulatskov.superapp.main.screens.service_screen.ServicePresenter
import ru.sulatskov.superapp.main.screens.textView.TextViewPresenter

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

    @Provides
    fun provideContactPresenter(): ContactPresenter {
        return ContactPresenter()
    }

    @Provides
    fun provideTextViewPresenter(): TextViewPresenter {
        return TextViewPresenter()
    }

    @Provides
    fun provideEditTextPresenter(): EditTextPresenter {
        return EditTextPresenter()
    }

}
