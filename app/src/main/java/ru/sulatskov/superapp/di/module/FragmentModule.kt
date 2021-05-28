package ru.sulatskov.superapp.di.module

import dagger.Module
import dagger.Provides
import ru.sulatskov.superapp.main.screens.app_bar_screen.AppBarPresenter
import ru.sulatskov.superapp.main.screens.button_screen.ButtonPresenter
import ru.sulatskov.superapp.main.screens.content_provider_screen.ContactPresenter
import ru.sulatskov.superapp.main.screens.edit_text_screen.EditTextPresenter
import ru.sulatskov.superapp.main.screens.general_screen.GeneralPresenter
import ru.sulatskov.superapp.main.screens.image_view_screen.ImageViewPresenter
import ru.sulatskov.superapp.main.screens.service_screen.ServicePresenter
import ru.sulatskov.superapp.main.screens.text_view_screen.TextViewPresenter

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

    @Provides
    fun provideButtonPresenter(): ButtonPresenter {
        return ButtonPresenter()
    }
    @Provides
    fun provideAppBarPresenter(): AppBarPresenter {
        return AppBarPresenter()
    }

    @Provides
    fun provideImageViewPresenter(): ImageViewPresenter {
        return ImageViewPresenter()
    }
}
