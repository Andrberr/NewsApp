package com.example.newsapp.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.ViewModelFactory
import com.example.feature.ui.SearchActivity
import com.example.newsapp.NewsApp
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val newsViewModel: NewsViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as NewsApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewmodel = newsViewModel

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val itemClick: (String) -> Unit = {
            val address = Uri.parse(it)
            val openLinkIntent = Intent(Intent.ACTION_VIEW, address)
            this.startActivity(openLinkIntent)
        }

        val adapter = NewsAdapter(itemClick)
        recycler.adapter = adapter

        newsViewModel.newsLiveData.observe(this) {
            adapter.setNews(it)
        }
        newsViewModel.getNewsList()

        newsViewModel.setToken("5193158c81d94ee7a0ce2981761b87d9")

        findViewById<Button>(R.id.searchButton).setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }
}