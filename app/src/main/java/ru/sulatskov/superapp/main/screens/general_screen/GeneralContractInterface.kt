package ru.sulatskov.superapp.main.screens.general_screen

import ru.sulatskov.superapp.base.presenter.BasePresenterInterface
import ru.sulatskov.superapp.base.view.BaseViewInterface

interface GeneralContractInterface {

    interface View : BaseViewInterface {
        fun openTextViewScreen()
        fun openServiceScreen()
        fun openContentProviderScreen()
        fun openEditTextScreen()
        fun openButtonScreen()
        fun openAppBarScreen()
    }

    interface Presenter : BasePresenterInterface<View> {
        fun onTextViewButtonClick()
        fun onServiceButtonClick()
        fun onContentProviderButtonClick()
        fun onEditTextButtonClick()
        fun onButtonScreenClick()
        fun onAppBarButtonClick()
    }
}