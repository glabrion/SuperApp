package ru.sulatskov.superapp.main.screens.recycler_view

import ru.sulatskov.superapp.base.presenter.BasePresenter

class RecyclerViewPresenter : BasePresenter<RecyclerViewContractInterface.View>(),
    RecyclerViewContractInterface.Presenter {

    override fun attach(view: RecyclerViewContractInterface.View) {
        super.attach(view)
        view.showList(getData())
    }

    private fun getData(): MutableList<String> {
        return mutableListOf<String>().apply {
            this.add("Волгоград")
            this.add("Волжский")
            this.add("Камышин")
        }
    }
}