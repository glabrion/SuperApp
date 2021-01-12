package ru.sulatskov.superapp.main.screens.editText

import ru.sulatskov.superapp.base.presenter.BasePresenter

class EditTextPresenter: BasePresenter<EditTextFragment>() {

    fun onLoginButtonClick() {
        view?.showLoginAndPassword()
    }
}