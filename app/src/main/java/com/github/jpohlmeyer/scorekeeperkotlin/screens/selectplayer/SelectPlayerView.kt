package com.github.jpohlmeyer.scorekeeperkotlin.screens.selectplayer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView

class SelectPlayerView(context: Context, attributeSet: AttributeSet) :
    AppCompatImageView(context, attributeSet) {

    // TODO
    //  - differentiate different fingers
    //  - different color for each finger
    //  - animation for countdown
    //  - choose one finger and display

    private var dot: PointF? = null

    private val circlePaint =
        Paint().apply {
            isAntiAlias = true
            color = Color.RED
            style = Paint.Style.FILL_AND_STROKE
        }

    init {
        setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                drawDot(event.x.toFloat(), event.y.toFloat())
            }
            this.performClick()
        }
    }

    fun drawDot(posX: Float, posY: Float) {
        this.dot = PointF(posX, posY)
        this.invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        this.dot?.apply {
            canvas.drawCircle(this.x, this.y, 50F, circlePaint)
        }
    }
}