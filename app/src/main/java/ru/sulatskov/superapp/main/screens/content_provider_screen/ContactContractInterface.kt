package ru.sulatskov.superapp.main.screens.content_provider_screen

import ru.sulatskov.superapp.base.presenter.BasePresenterInterface
import ru.sulatskov.superapp.base.view.BaseViewInterface

interface ContactContractInterface {
    interface View : BaseViewInterface {
        fun showToastNotPermission()
        fun showContact()
        fun showToastContactsIsEmpty()
        fun getContactList(): List<String>
    }

    interface Presenter : BasePresenterInterface<View>
}