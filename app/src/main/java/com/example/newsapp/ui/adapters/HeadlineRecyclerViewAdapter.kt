package com.example.newsapp.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.models.Headline
import com.example.newsapp.ui.viewHolders.HeadlineViewHolder
import com.example.newsapp.utils.EspressoIdlingResource

class HeadlineRecyclerViewAdapter(private val headlines:List<Headline>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HeadlineViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HeadlineViewHolder).bind(headlines[position])
    }

    override fun getItemCount(): Int {
        return headlines.size
    }

}