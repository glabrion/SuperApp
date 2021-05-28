package ru.sulatskov.superapp.main.screens.image_view_screen

import ru.sulatskov.superapp.base.presenter.BasePresenter

class ImageViewPresenter : BasePresenter<ImageViewContractInterface.View>(),
    ImageViewContractInterface.Presenter {

    override fun onSelectImageClick() {
        view?.selectImage()
    }
}