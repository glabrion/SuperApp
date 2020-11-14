package ru.sulatskov.hiltexample.main.screens.general

import ru.sulatskov.hiltexample.base.presenter.BasePresenter

class GeneralPresenter: BasePresenter<GeneralFragment>() {
    fun onGetToastClick() {
        view?.showToast()
    }

}