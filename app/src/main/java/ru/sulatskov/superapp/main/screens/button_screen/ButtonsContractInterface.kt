package ru.sulatskov.superapp.main.screens.button_screen

import ru.sulatskov.superapp.base.presenter.BasePresenterInterface
import ru.sulatskov.superapp.base.view.BaseViewInterface

interface ButtonsContractInterface {
    interface View : BaseViewInterface {
        fun showToast(type: ButtonPresenter.ToastType)
        fun startProgress()
        fun stopProgress()
    }

    interface Presenter : BasePresenterInterface<View> {
        fun onImageButtonClick()
        fun onButtonDrawableClick()
        fun onProgressButtonClick()
    }
}