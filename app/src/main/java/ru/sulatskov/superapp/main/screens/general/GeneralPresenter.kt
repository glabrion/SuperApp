package ru.sulatskov.superapp.main.screens.general

import ru.sulatskov.superapp.base.presenter.BasePresenter

class GeneralPresenter: BasePresenter<GeneralFragment>() {
    fun onServiceButtonClick() {
        view?.openServiceScreen()
    }

    fun onContentProviderButtonClick() {
        view?.openContentProviderScreen()
    }

}