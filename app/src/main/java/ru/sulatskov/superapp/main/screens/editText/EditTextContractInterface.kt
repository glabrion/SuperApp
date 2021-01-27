package ru.sulatskov.superapp.main.screens.editText

import ru.sulatskov.superapp.base.presenter.BasePresenterInterface
import ru.sulatskov.superapp.base.view.BaseViewInterface

interface EditTextContractInterface {
    interface View : BaseViewInterface {
        fun showLoginAndPassword()
    }

    interface Presenter : BasePresenterInterface<View> {
        fun onLoginButtonClick()
    }
}