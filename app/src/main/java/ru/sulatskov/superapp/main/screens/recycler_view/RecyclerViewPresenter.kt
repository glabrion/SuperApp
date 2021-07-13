package ru.sulatskov.superapp.main.screens.recycler_view

import ru.sulatskov.superapp.base.presenter.BasePresenter

class RecyclerViewPresenter : BasePresenter<RecyclerViewContractInterface.View>(),
    RecyclerViewContractInterface.Presenter {

    override fun attach(view: RecyclerViewContractInterface.View) {
        super.attach(view)
        view.showList(getData())
    }

    private fun getData(): MutableList<Pair<Any, Type>> {
        return mutableListOf<Pair<Any, Type>>().apply {
            this.add(Pair("Россия", Country))
            this.add(Pair(CityDto("Волгоград", 1_000_000), City))
            this.add(Pair(CityDto("Москва", 10_000_000), City))
            this.add(Pair(CityDto("Волжский", 300_000), City))
            this.add(Pair(CityDto("Город1", 10_000), City))
            this.add(Pair(CityDto("Город2", 20_000), City))
            this.add(Pair(CityDto("Город3", 30_000), City))
            this.add(Pair(CityDto("Город4", 40_000), City))
            this.add(Pair(CityDto("Город5", 50_000), City))
            this.add(Pair(CityDto("Город6", 60_000), City))
            this.add(Pair(CityDto("Город7", 70_000), City))
            this.add(Pair(CityDto("Город8", 80_000), City))
            this.add(Pair(CityDto("Город9", 90_000), City))
            this.add(Pair(CityDto("Город10", 100_000), City))
            this.add(Pair(CityDto("Город11", 110_000), City))
            this.add(Pair(CityDto("Город12", 120_000), City))
            this.add(Pair(CityDto("Город13", 130_000), City))
            this.add(Pair(CityDto("Город14", 140_000), City))
            this.add(Pair(CityDto("Город15", 150_000), City))
            this.add(Pair(CityDto("Город16", 160_000), City))
            this.add(Pair(CityDto("Город17", 170_000), City))
            this.add(Pair(CityDto("Город18", 180_000), City))
            this.add(Pair(CityDto("Город19", 190_000), City))
            this.add(Pair(CityDto("Город20", 200_000), City))
            this.add(Pair("Беларусь", Country))
            this.add(Pair(CityDto("Минск", 1_500_000), City))
            this.add(Pair(CityDto("Гродно", 100_000), City))
            this.add(Pair(CityDto("Город1", 100_000), City))
            this.add(Pair(CityDto("Город2", 200_000), City))
            this.add(Pair(CityDto("Город3", 300_000), City))
            this.add(Pair(CityDto("Город4", 400_000), City))
            this.add(Pair(CityDto("Город5", 500_000), City))
            this.add(Pair(CityDto("Город6", 600_000), City))
            this.add(Pair(CityDto("Город7", 700_000), City))
            this.add(Pair(CityDto("Город8", 800_000), City))
            this.add(Pair(CityDto("Город9", 900_000), City))
            this.add(Pair(CityDto("Город10", 1000_000), City))
            this.add(Pair(CityDto("Город11", 1100_000), City))
            this.add(Pair(CityDto("Город12", 1200_000), City))
            this.add(Pair(CityDto("Город13", 1300_000), City))
            this.add(Pair(CityDto("Город14", 1400_000), City))
            this.add(Pair(CityDto("Город15", 1500_000), City))
            this.add(Pair(CityDto("Город16", 1600_000), City))
            this.add(Pair(CityDto("Город17", 1700_000), City))
            this.add(Pair(CityDto("Город18", 1800_000), City))
        }
    }
}

class CityDto(val name: String, val population: Int)

sealed class Type
object City : Type()
object Country : Type()