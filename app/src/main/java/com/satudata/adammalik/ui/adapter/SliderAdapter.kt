package com.satudata.adammalik.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.satudata.adammalik.R
import com.satudata.adammalik.databinding.SliderItemBinding

//import com.satudata.adammalik.databinding.SliderItemBinding

class SliderAdapter(val listTitle: Array<String>, val listSubtitle: Array<String>) :
    RecyclerView.Adapter<SliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            SliderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.itemView.setBackgroundColor(list[position])
        val title = listTitle[position]
        val subTitle = listSubtitle[position]
        holder.binding.title.text = title
        holder.binding.subTitle.text = subTitle
    }

    override fun getItemCount(): Int = listTitle.size

    class ViewHolder(val binding: SliderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}