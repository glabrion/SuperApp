package ru.sulatskov.superapp.main.screens.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.sulatskov.superapp.databinding.ItemCityBinding
import ru.sulatskov.superapp.databinding.ItemCountryBinding

class PhotosAdapter(private val photoListClickListener: CityClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = mutableListOf<Pair<Any, Type>>()

    companion object {
        const val TYPE_CITY = 1
        const val TYPE_COUNTRY = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_CITY) {
            val binding = ItemCityBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            CityViewHolder(binding)
        } else {
            val binding = ItemCountryBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            CountryViewHolder(binding)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (list[position].second) {
            City -> TYPE_CITY
            Country -> TYPE_COUNTRY
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CityViewHolder -> holder.bind(list[position], photoListClickListener)
            is CountryViewHolder -> holder.bind(list[position], photoListClickListener)
        }
    }

    override fun getItemCount() = list.size

    fun setData(items: List<Pair<Any, Type>>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun isHeader(position: Int) = getItemViewType(position) == TYPE_COUNTRY

    class CityViewHolder(private val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Pair<Any, Type>, cityClickListener: CityClickListener) {
            binding.name.text = (data.first as? CityDto)?.name
            binding.population.text = (data.first as? CityDto)?.population.toString()
            itemView.setOnClickListener {
                cityClickListener.onCityClick((data.first as? CityDto)?.name)
            }
        }
    }

    class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Pair<Any, Type>, cityClickListener: CityClickListener) {
            binding.name.text = data.first as? String
            itemView.setOnClickListener {
                cityClickListener.onCityClick((data.first as? CityDto)?.name)
            }
        }
    }
}

interface CityClickListener {
    fun onCityClick(name: String?)
}
