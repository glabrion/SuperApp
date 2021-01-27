package ru.sulatskov.superapp.main.screens.service_screen

import ru.sulatskov.superapp.base.presenter.BasePresenterInterface
import ru.sulatskov.superapp.base.view.BaseViewInterface

interface ServiceContractInterface {
    interface View : BaseViewInterface {
        fun showStartServiceToast()
        fun showErrorToast()
        fun showStopServiceToast()
        fun showServiceFinishedToast()
        fun startService()
        fun stopService()
    }

    interface Presenter : BasePresenterInterface<View> {
        fun onStartServiceClick()
        fun onStopServiceClick()
    }
}