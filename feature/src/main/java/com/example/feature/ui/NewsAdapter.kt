package com.example.feature.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.News
import com.example.feature.databinding.NewsLayoutBinding

class NewsAdapter() : RecyclerView.Adapter<NewsViewHolder>() {

    private val news = mutableListOf<News>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val newsLayoutBinding = NewsLayoutBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(newsLayoutBinding)
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position])
    }

    fun setNews(news: List<News>) {
        this.news.clear()
        this.news.addAll(news)
        notifyDataSetChanged()
    }
}