package com.example.experimentalfeaturespoc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SampleAdapter(private val dataSet: Array<Int>) :
    RecyclerView.Adapter<SampleAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rv: RecyclerView

        init {
            // Define click listener for the ViewHolder's View.
            rv = view.findViewById(R.id.rv_items)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.sample_layout, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val adapter = SampleAdapter1(listOf(1, 2, 3, 4, 5, 6, 7, 8))

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.rv.adapter = adapter
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}