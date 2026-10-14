package com.github.jpohlmeyer.scorekeeperkotlin.screens.selectplayer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView

class SelectPlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var touchPoints: Map<Int, TouchPoint> = emptyMap()
    private var selectedPointerId: Int? = null

    var onTouchEventListener: ((pointerId: Int, x: Float, y: Float, isRemoved: Boolean) -> Unit)? = null

    private val circlePaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    private val strokePaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = 8f
        color = Color.WHITE
    }

    private val selectedRingPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = 12f
        color = Color.YELLOW
    }

    private val textPaint = Paint().apply {
        isAntiAlias = true
        color = Color.WHITE
        textSize = 48f
        textAlign = Paint.Align.CENTER
    }

    fun updateData(points: Map<Int, TouchPoint>, selectedId: Int?) {
        this.touchPoints = points
        this.selectedPointerId = selectedId
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val actionMasked = event.actionMasked
        val pointerIndex = event.actionIndex
        val pointerId = event.getPointerId(pointerIndex)

        when (actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                performClick()
                val x = event.getX(pointerIndex)
                val y = event.getY(pointerIndex)
                onTouchEventListener?.invoke(pointerId, x, y, false)
            }
            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until event.pointerCount) {
                    val id = event.getPointerId(i)
                    val x = event.getX(i)
                    val y = event.getY(i)
                    onTouchEventListener?.invoke(id, x, y, false)
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_CANCEL -> {
                performClick()
                val x = event.getX(pointerIndex)
                val y = event.getY(pointerIndex)
                onTouchEventListener?.invoke(pointerId, x, y, true)
            }
        }
        return true
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for ((id, point) in touchPoints) {
            val isSelected = (id == selectedPointerId)
            val radius = if (isSelected) 160f else 130f

            circlePaint.color = point.color
            canvas.drawCircle(point.x, point.y, radius, circlePaint)
            canvas.drawCircle(point.x, point.y, radius, strokePaint)

            if (isSelected) {
                canvas.drawCircle(point.x, point.y, radius + 20f, selectedRingPaint)
            }
        }
    }
}
