package com.example.experimentalfeaturespoc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.experimentalfeaturespoc.databinding.SampleItemBinding

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {
    private var itemsList = arrayListOf<Int>()

    inner class ItemsViewHolder(val itemBinding: SampleItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(SampleItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        with(holder.itemBinding) {
            tvProductName.text = "product ${itemsList[position]}"
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun submitList(itemsList: ArrayList<Int>) {
        this.itemsList.clear()
        this.itemsList = itemsList
    }


}