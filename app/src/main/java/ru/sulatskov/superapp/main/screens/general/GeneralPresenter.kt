package ru.sulatskov.superapp.main.screens.general

import ru.sulatskov.superapp.base.presenter.BasePresenter

class GeneralPresenter : BasePresenter<GeneralContractInterface.View>(),
    GeneralContractInterface.Presenter {

    override fun onServiceButtonClick() {
        view?.openServiceScreen()
    }

    override fun onContentProviderButtonClick() {
        view?.openContentProviderScreen()
    }

    override fun onTextViewButtonClick() {
        view?.openTextViewScreen()
    }

    override fun onEditTextButtonClick() {
        view?.openEditTextScreen()
    }
}