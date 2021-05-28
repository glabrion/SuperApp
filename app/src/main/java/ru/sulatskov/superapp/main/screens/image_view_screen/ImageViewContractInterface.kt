package ru.sulatskov.superapp.main.screens.image_view_screen

import ru.sulatskov.superapp.base.presenter.BasePresenterInterface
import ru.sulatskov.superapp.base.view.BaseViewInterface

interface ImageViewContractInterface {
    interface View : BaseViewInterface {
        fun selectImageFromGallery()
        fun selectImageFromCamera()
    }

    interface Presenter : BasePresenterInterface<View> {
        fun onSelectImageFromGalleryClick()
        fun onSelectImageFromCameraClick()
    }
}