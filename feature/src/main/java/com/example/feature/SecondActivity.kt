package com.example.feature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.core.findDependencies
import com.example.feature.di.DaggerFeatureComponent

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFeatureComponent.factory().create(findDependencies()).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}