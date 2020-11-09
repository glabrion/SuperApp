package ru.sulatskov.superapp.main.screens.service_screen

import ru.sulatskov.superapp.base.presenter.BasePresenter

class ServicePresenter: BasePresenter<ServiceFragment>() {
    fun onStartServiceClick() {
        view?.startService()
    }

    fun onStopServiceClick() {
        view?.stopService()
    }

}