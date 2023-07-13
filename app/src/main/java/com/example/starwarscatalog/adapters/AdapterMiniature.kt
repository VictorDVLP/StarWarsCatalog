package com.example.starwarscatalog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarscatalog.data.PeopleDb
import com.example.starwarscatalog.databinding.MiniatureDetailBinding
import com.example.starwarscatalog.getDrawableImage
import okhttp3.internal.Util

class AdapterMiniature(private val listdata: List<String>, private val actionDetail: (String) -> Unit ):
    RecyclerView.Adapter<AdapterMiniature.ViewHolder>() {

    inner class ViewHolder(val binding: MiniatureDetailBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MiniatureDetailBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            imageDetail.setImageResource(imageDetail.context.getDrawableImage(listdata[position]))
            imageDetail.setOnClickListener {
                actionDetail.invoke(listdata[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return listdata.size
    }
}