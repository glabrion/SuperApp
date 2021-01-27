package ru.sulatskov.superapp.main.screens.service_screen

import ru.sulatskov.superapp.base.presenter.BasePresenter

class ServicePresenter : BasePresenter<ServiceContractInterface.View>(),
    ServiceContractInterface.Presenter {
    override fun onStartServiceClick() {
        view?.startService()
    }

    override fun onStopServiceClick() {
        view?.stopService()
    }

}