package com.example.newsapp.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.News
import com.example.newsapp.databinding.NewsLayoutBinding

class NewsViewHolder(private val newsLayoutBinding: NewsLayoutBinding, private val itemClick: (String) -> Unit) :
    RecyclerView.ViewHolder(newsLayoutBinding.root) {
    fun bind(news: News) {
        newsLayoutBinding.author.text = news.author
        newsLayoutBinding.title.text = news.title
        newsLayoutBinding.description.text = news.description

        Glide.with(newsLayoutBinding.root.context)
            .load(news.imageUrl)
            .into(newsLayoutBinding.image)

        newsLayoutBinding.root.setOnClickListener {
            itemClick.invoke(news.link)
        }
    }
}