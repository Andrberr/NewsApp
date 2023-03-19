package com.example.newsapp.ui.utils

import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView

object LottieBinding {
    @JvmStatic
    @BindingAdapter("goneUnless")
    fun LottieAnimationView.setVisibility(visibility: Boolean?) {
        if (visibility != null) {
            this.isVisible = visibility
        }
    }
}