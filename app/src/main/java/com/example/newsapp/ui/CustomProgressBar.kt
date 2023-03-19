package com.example.newsapp.ui

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View


class CustomProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val animator = ObjectAnimator.ofFloat(this, ROTATION, 0f, 5000f).apply {
        duration = 10000
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 15F
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val oval = RectF().apply {
            left = (width / 2 - width / 12).toFloat()
            top = (height / 2 - height / 18).toFloat()
            right = (width / 2 + width / 12).toFloat()
            bottom = (height / 2 + height / 18).toFloat()
        }

        canvas.drawArc(oval, 135F, 270F, false, paint)
        animator.start()
    }
}