package ru.sulatskov.superapp.main.screens.edit_text_screen

import ru.sulatskov.superapp.base.presenter.BasePresenter

class EditTextPresenter : BasePresenter<EditTextContractInterface.View>(),
    EditTextContractInterface.Presenter {

    override fun onLoginButtonClick() {
        view?.showLoginAndPassword()
    }
}