package ru.sulatskov.superapp.main.screens.general

import ru.sulatskov.superapp.base.presenter.BasePresenterInterface
import ru.sulatskov.superapp.base.view.BaseViewInterface

interface GeneralContractInterface {

    interface View : BaseViewInterface {
        fun openTextViewScreen()
        fun openServiceScreen()
        fun openContentProviderScreen()
        fun openEditTextScreen()
    }

    interface Presenter : BasePresenterInterface<View> {
        fun onTextViewButtonClick()
        fun onServiceButtonClick()
        fun onContentProviderButtonClick()
        fun onEditTextButtonClick()
    }
}