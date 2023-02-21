package com.example.newsapp.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import java.net.SocketTimeoutException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val newsViewModel by viewModels<NewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val itemClick: (String) -> Unit = {
            val address = Uri.parse(it)
            val openLinkIntent = Intent(Intent.ACTION_VIEW, address)
            this.startActivity(openLinkIntent)
        }

        val adapter = NewsAdapter(itemClick)
        recycler.adapter = adapter

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        newsViewModel.newsLiveData.observe(this) {
            progressBar.isVisible = false
            adapter.setNews(it)
        }
        newsViewModel.getNewsList()

        newsViewModel.errorLiveData.observe(this) {
            Toast.makeText(this, getString(it), Toast.LENGTH_SHORT).show()
        }

        newsViewModel.loadingLiveData.observe(this) {
            progressBar.isVisible = true
        }

        newsViewModel.setToken("5193158c81d94ee7a0ce2981761b87d9")
    }
}