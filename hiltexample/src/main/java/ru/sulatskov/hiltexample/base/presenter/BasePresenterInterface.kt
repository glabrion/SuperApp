package ru.sulatskov.hiltexample.base.presenter

import ru.sulatskov.hiltexample.base.view.BaseViewInterface

interface BasePresenterInterface<V: BaseViewInterface> {

    val isAttached: Boolean

    fun attach(view: V)

    fun detach()

}