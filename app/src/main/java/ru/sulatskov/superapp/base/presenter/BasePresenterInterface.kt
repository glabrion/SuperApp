package ru.sulatskov.superapp.base.presenter

import ru.sulatskov.superapp.base.view.BaseViewInterface

interface BasePresenterInterface<V: BaseViewInterface> {

    val isAttached: Boolean

    fun attach(view: V)

    fun detach()

}