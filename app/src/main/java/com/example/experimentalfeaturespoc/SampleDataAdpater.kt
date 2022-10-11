package com.example.experimentalfeaturespoc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.experimentalfeaturespoc.databinding.SampleItemBinding

class SampleDataAdpater : RecyclerView.Adapter<SampleDataAdpater.SampleDataViewHolder>() {

    private var itemsList = arrayListOf<Int>()

    class SampleDataViewHolder(val itemBinding: SampleItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleDataViewHolder {
        return SampleDataViewHolder(SampleItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SampleDataViewHolder, position: Int) {
        val item = itemsList[position]
        with(holder.itemBinding) {
            tvProductName.text ="Product $item"
        }
    }

    override fun getItemCount(): Int {
        return  itemsList.size
    }

    fun submitLit(list: ArrayList<Int>) {
        itemsList.clear()
        itemsList = list
        notifyDataSetChanged()
    }
}