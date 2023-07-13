package com.example.starwarscatalog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarscatalog.data.SpeciesDb
import com.example.starwarscatalog.databinding.ItemBinding
import com.example.starwarscatalog.getDrawableImage

class SpecieAdapter(
    private val onItemClick: (SpeciesDb) -> Unit
): PagingDataAdapter<SpeciesDb ,SpecieAdapter.ViewHolder>(DiffUtilCallback) {

    inner class ViewHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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

    object DiffUtilCallback: DiffUtil.ItemCallback<SpeciesDb>() {
        override fun areItemsTheSame(oldItem: SpeciesDb, newItem: SpeciesDb): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: SpeciesDb, newItem: SpeciesDb): Boolean {
            return oldItem == newItem
        }
    }
}