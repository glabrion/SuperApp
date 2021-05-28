package ru.sulatskov.superapp.main.screens.app_bar_screen

import ru.sulatskov.superapp.base.presenter.BasePresenterInterface
import ru.sulatskov.superapp.base.view.BaseViewInterface

interface AppBarContractInterface {
    interface View : BaseViewInterface {
        fun showText(newText: String?)
    }

    interface Presenter : BasePresenterInterface<View> {
        fun onSearchChange(newText: String?)
    }
}