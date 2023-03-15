package com.example.feature

import androidx.lifecycle.ViewModel
import com.example.domain.Repository
import javax.inject.Inject

class SecondViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {
}