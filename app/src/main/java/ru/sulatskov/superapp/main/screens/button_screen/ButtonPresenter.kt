package ru.sulatskov.superapp.main.screens.button_screen

import kotlinx.coroutines.*
import ru.sulatskov.superapp.base.presenter.BasePresenter
import kotlin.coroutines.CoroutineContext

class ButtonPresenter: BasePresenter<ButtonsContractInterface.View>(), ButtonsContractInterface.Presenter, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    override fun onImageButtonClick() {
        view?.showToast(ToastType.IMAGE_BUTTON)
    }

    override fun onButtonDrawableClick() {
        view?.showToast(ToastType.IMAGE_DRAWABLE)
    }

    override fun onProgressButtonClick() {
        launch {
            view?.startProgress()
            delay(3000)
            view?.showToast(ToastType.PROGRESS_COMPLETE)
            view?.stopProgress()
        }
    }

    enum class ToastType{
        IMAGE_BUTTON,
        IMAGE_DRAWABLE,
        PROGRESS_COMPLETE
    }
}