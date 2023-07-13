package com.example.starwarscatalog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarscatalog.data.StarshipDb
import com.example.starwarscatalog.databinding.ItemBinding
import com.example.starwarscatalog.getDrawableImage

class StarshipAdapter(
    private val onItemClick: (StarshipDb) -> Unit
) : PagingDataAdapter<StarshipDb ,StarshipAdapter.Viewholder>(DiffUtilCallback) {

    inner class Viewholder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(ItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            namePerson.text = item?.name
            if (item != null) {
                mainImagen.setImageResource(mainImagen.context.getDrawableImage(item.url))
            }
            mainImagen.setOnClickListener {
                if (item != null) {
                    onItemClick.invoke(item)
                }
            }
        }
    }

    object DiffUtilCallback: DiffUtil.ItemCallback<StarshipDb>() {
        override fun areItemsTheSame(oldItem: StarshipDb, newItem: StarshipDb): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: StarshipDb, newItem: StarshipDb): Boolean {
            return oldItem == newItem
        }
    }
}