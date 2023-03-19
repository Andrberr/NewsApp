package com.example.feature

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.News
import com.example.feature.databinding.NewsLayoutBinding

class NewsViewHolder(private val newsLayoutBinding: NewsLayoutBinding) :
    RecyclerView.ViewHolder(newsLayoutBinding.root) {
    fun bind(news: News) {
        newsLayoutBinding.author.text = news.author
        newsLayoutBinding.title.text = news.title
        newsLayoutBinding.description.text = news.description

        Glide.with(newsLayoutBinding.root.context)
            .load(news.imageUrl)
            .into(newsLayoutBinding.image)
    }
}