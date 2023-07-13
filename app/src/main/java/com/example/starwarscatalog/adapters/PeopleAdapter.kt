package com.example.starwarscatalog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarscatalog.data.PeopleDb
import com.example.starwarscatalog.databinding.ItemBinding
import com.example.starwarscatalog.getDrawableImage

class PeopleAdapter(
    private val onItemClick: (PeopleDb) -> Unit
) : PagingDataAdapter<PeopleDb, PeopleAdapter.ViewHolder>(DiffUtilCallback) {

    inner class ViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

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
}

object DiffUtilCallback : DiffUtil.ItemCallback<PeopleDb>() {
    override fun areItemsTheSame(oldItem: PeopleDb, newItem: PeopleDb): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: PeopleDb, newItem: PeopleDb): Boolean {
        return oldItem == newItem
    }

}