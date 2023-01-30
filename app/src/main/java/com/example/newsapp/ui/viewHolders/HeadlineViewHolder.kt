package com.example.newsapp.ui.viewHolders

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.data.models.Headline
import com.example.newsapp.databinding.ItemLatestNewsBinding

class HeadlineViewHolder(private val binding:ItemLatestNewsBinding): RecyclerView.ViewHolder(binding.parent) {

    private val TAG = "HeadlineViewHolder"

    companion object{
        fun create(viewGroup:ViewGroup): HeadlineViewHolder{
            val binding = ItemLatestNewsBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return HeadlineViewHolder(binding)
        }
    }

    fun bind(headline: Headline) = binding.apply {
        Log.i(TAG, "bind: $headline")
        tvAuthor.text = headline.author
        tvHeadline.text = headline.title
        tvDescription.text = headline.description

        Glide.with(itemView)
            .load(headline.urlToImage)
            .into(ivHeadline)
    }

}