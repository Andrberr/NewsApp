package com.example.newsapp.ui.utils

import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.example.newsapp.ui.CustomProgressBar

object ProgressBarBinding {
    @JvmStatic
    @BindingAdapter ("goneUnless")
    fun CustomProgressBar.setVisibility(visibility: Boolean?){
        if (visibility!=null){
            this.isVisible = visibility
        }
    }
}