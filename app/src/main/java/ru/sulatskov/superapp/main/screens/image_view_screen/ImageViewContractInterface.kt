package ru.sulatskov.superapp.main.screens.image_view_screen

import ru.sulatskov.superapp.base.presenter.BasePresenterInterface
import ru.sulatskov.superapp.base.view.BaseViewInterface

interface ImageViewContractInterface {
    interface View : BaseViewInterface {
        fun selectImage()
    }

    interface Presenter : BasePresenterInterface<View> {
        fun onSelectImageClick()
    }
}