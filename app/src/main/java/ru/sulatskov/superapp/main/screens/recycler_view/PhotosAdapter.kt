package ru.sulatskov.superapp.main.screens.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.sulatskov.superapp.databinding.ItemCityBinding

class PhotosAdapter(private val photoListClickListener: CityClickListener) :
    RecyclerView.Adapter<PhotosAdapter.PhotoCardViewHolder>() {

    private val list = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoCardViewHolder {
        val binding = ItemCityBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoCardViewHolder, position: Int) {
        holder.bind(list[position], photoListClickListener)
    }

    override fun getItemCount() = list.size

    fun setData(items: List<String>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    class PhotoCardViewHolder(private val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(name: String, cityClickListener: CityClickListener) {
            binding.name.text = name
            itemView.setOnClickListener {
                cityClickListener.onCityClick(name)
            }
        }
    }
}

interface CityClickListener {
    fun onCityClick(name: String?)
}
