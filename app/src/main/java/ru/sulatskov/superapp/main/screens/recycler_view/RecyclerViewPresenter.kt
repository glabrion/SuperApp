package ru.sulatskov.superapp.main.screens.recycler_view

import ru.sulatskov.superapp.base.presenter.BasePresenter

class RecyclerViewPresenter : BasePresenter<RecyclerViewContractInterface.View>(),
    RecyclerViewContractInterface.Presenter {

    override fun attach(view: RecyclerViewContractInterface.View) {
        super.attach(view)
        view.showList(getData())
    }

    private fun getData(): MutableList<Any> {
        return mutableListOf<Any>().apply {
            this.add("Россия")
            this.add(CityDto("Волгоград", 1_000_000))
            this.add(CityDto("Москва", 10_000_000))
            this.add(CityDto("Волжский", 300_000))
            this.add(CityDto("Город1", 10_000))
            this.add(CityDto("Город2", 20_000))
            this.add(CityDto("Город3", 30_000))
            this.add(CityDto("Город4", 40_000))
            this.add(CityDto("Город5", 50_000))
            this.add(CityDto("Город6", 60_000))
            this.add(CityDto("Город7", 70_000))
            this.add(CityDto("Город8", 80_000))
            this.add(CityDto("Город9", 90_000))
            this.add(CityDto("Город10", 100_000))
            this.add(CityDto("Город11", 110_000))
            this.add(CityDto("Город12", 120_000))
            this.add(CityDto("Город13", 130_000))
            this.add(CityDto("Город14", 140_000))
            this.add(CityDto("Город15", 150_000))
            this.add(CityDto("Город16", 160_000))
            this.add(CityDto("Город17", 170_000))
            this.add(CityDto("Город18", 180_000))
            this.add(CityDto("Город19", 190_000))
            this.add(CityDto("Город20", 200_000))
            this.add("Беларусь")
            this.add(CityDto("Минск", 1_500_000))
            this.add(CityDto("Гродно", 100_000))
            this.add(CityDto("Город1", 100_000))
            this.add(CityDto("Город2", 200_000))
            this.add(CityDto("Город3", 300_000))
            this.add(CityDto("Город4", 400_000))
            this.add(CityDto("Город5", 500_000))
            this.add(CityDto("Город6", 600_000))
            this.add(CityDto("Город7", 700_000))
            this.add(CityDto("Город8", 800_000))
            this.add(CityDto("Город9", 900_000))
            this.add(CityDto("Город10", 1000_000))
            this.add(CityDto("Город11", 1100_000))
            this.add(CityDto("Город12", 1200_000))
            this.add(CityDto("Город13", 1300_000))
            this.add(CityDto("Город14", 1400_000))
            this.add(CityDto("Город15", 1500_000))
            this.add(CityDto("Город16", 1600_000))
            this.add(CityDto("Город17", 1700_000))
            this.add(CityDto("Город18", 1800_000))
        }
    }
}

class CityDto(val name: String, val population: Int)