package com.aram.paexamtest.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.aram.paexamtest.R
import kotlin.math.min

class CustomSickBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {
    private val LOG = "TAG"

    private val paint = Paint().apply {
        color = DEF_START_COLOR
        strokeWidth = DEF_STROK_WIDTH
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
        //style = Paint.Style.STROKE
    }
    private lateinit var shader: Shader

    private var touchX = 0f
        set(value) {
            field = value
            invalidate()
        }
    var progress = MIN_PROGRESS
        set(value) {
            field = when {
                value > 1f -> 1f
                value < 0f -> 0f
                else -> value
            }
            invalidate()
        }


    init {

        context.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.CustomSickBar,
            defStyle,
            0
        )?.apply {

            DEF_START_COLOR = getColor(R.styleable.CustomSickBar_start_color, DEF_START_COLOR)
            DEF_END_COLOR = getColor(R.styleable.CustomSickBar_end_color, DEF_END_COLOR)
            progress = getFloat(R.styleable.CustomSickBar_progress, MIN_PROGRESS)
            DEF_STROK_WIDTH =
                getDimension(R.styleable.CustomSickBar_strok_width, DEF_STROK_WIDTH)

            shader = LinearGradient(
                0f,
                0f,
                DEF_STROK_WIDTH * 10,
                0f,
                DEF_START_COLOR,
                DEF_END_COLOR,
                Shader.TileMode.MIRROR
            )
             paint.strokeWidth = DEF_STROK_WIDTH
             paint.shader = shader

             recycle()
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val requestedWidth = MeasureSpec.getSize(widthMeasureSpec)
        val requestedWidthMode = MeasureSpec.getMode(widthMeasureSpec)

        val requestedHeight = MeasureSpec.getSize(heightMeasureSpec)
        val requestedHeightMode = MeasureSpec.getMode(heightMeasureSpec)

        val desiredWidth = (DEF_STROK_WIDTH * 10).toInt()
        val desiredHeight = DEF_STROK_WIDTH.toInt()

        val width = when (requestedWidthMode) {
            MeasureSpec.EXACTLY -> requestedWidth
            MeasureSpec.UNSPECIFIED -> desiredWidth
            else -> min(requestedWidth, desiredWidth)
        }

        val height = when (requestedHeightMode) {
            MeasureSpec.EXACTLY -> requestedHeight
            MeasureSpec.UNSPECIFIED -> desiredWidth
            else -> min(requestedHeight, desiredHeight)
        }

        setMeasuredDimension(width, height)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> touchX = event.x
            MotionEvent.ACTION_MOVE -> touchX = event.x
        }
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.run {
            if (touchX == 0f) {
                Log.d(LOG, "tocjx = 0f")
                drawLine(
                    0f,
                    (height / 2).toFloat(),
                    width * progress,
                    (height / 2).toFloat(),
                    paint
                )
            } else {
                drawLine(0f, (height / 2).toFloat(), touchX, (height / 2).toFloat(), paint)
            }
        }
    }

    companion object {
        var DEF_START_COLOR = Color.GRAY
        var DEF_END_COLOR = Color.BLUE
        var MIN_PROGRESS = 0f
        var MAX_PROGRESS = 1f
        var DEF_STROK_WIDTH = 20f
    }
}