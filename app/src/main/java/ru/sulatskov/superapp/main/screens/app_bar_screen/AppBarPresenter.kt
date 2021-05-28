package ru.sulatskov.superapp.main.screens.app_bar_screen

import ru.sulatskov.superapp.base.presenter.BasePresenter

class AppBarPresenter: BasePresenter<AppBarContractInterface.View>(), AppBarContractInterface.Presenter{
    override fun onSearchChange(newText: String?) {
        view?.showText(newText)
    }

}