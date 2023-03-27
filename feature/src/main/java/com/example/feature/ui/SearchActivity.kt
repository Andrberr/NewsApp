package com.example.feature.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.ViewModelFactory
import com.example.core.findDependencies
import com.example.feature.R
import com.example.feature.di.DaggerFeatureComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val searchViewModel: SearchViewModel by viewModels { factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFeatureComponent.factory().create(findDependencies()).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        val newsAdapter = NewsAdapter()
        with(recycler) {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        this.lifecycleScope.launch {
            searchViewModel.results
                .flowWithLifecycle(
                    this@SearchActivity.lifecycle,
                    Lifecycle.State.STARTED
                )
                .distinctUntilChanged()
                .collect { list ->
                    newsAdapter.setNews(list)
                }
        }

        val editText = findViewById<EditText>(R.id.editText)
        editText.addTextChangedListener { text ->
            if (text.toString() != "") {
                searchViewModel.setQuery(text.toString())
            } else newsAdapter.setNews(emptyList())
        }
    }
}