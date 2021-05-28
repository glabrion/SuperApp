package ru.sulatskov.superapp.main.screens.toolbar_screen

import ru.sulatskov.superapp.base.presenter.BasePresenterInterface
import ru.sulatskov.superapp.base.view.BaseViewInterface

interface ToolbarContractInterface {
    interface View : BaseViewInterface

    interface Presenter : BasePresenterInterface<View>
}