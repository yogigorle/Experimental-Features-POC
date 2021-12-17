package com.example.experimentalfeaturespoc

import android.content.Context
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


private enum class FanSpeed(val label: Int) {
    OFF(R.string.fan_off),
    LOW(R.string.fan_low),
    MEDIUM(R.string.fan_medium),
    HIGH(R.string.fan_high);

    fun next() = when (this) {
        OFF -> LOW
        LOW -> MEDIUM
        MEDIUM -> HIGH
        HIGH -> OFF
    }
}

private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35

private var fanSpeedLowColor = 0
private var fanSpeedMediumColor = 0
private var fanSpeedMaxColor = 0

class DialView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    //define necesary variables
    private var circleRadius = 0.0f
    private var fanSpeed = FanSpeed.OFF

    //helps to draw view based on x and y position
    private var pointPosition = PointF(0.0f, 0.0f)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.DEFAULT_BOLD
    }

    init {
        isClickable = true
        context.withStyledAttributes(attrs, R.styleable.DialView) {
            fanSpeedLowColor = getColor(R.styleable.DialView_fanColor1, 0)
            fanSpeedMediumColor = getColor(R.styleable.DialView_fanColor2, 0)
            fanSpeedMaxColor = getColor(R.styleable.DialView_fanColor3, 0)
        }
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true

        fanSpeed = fanSpeed.next()
        contentDescription = resources.getString(fanSpeed.label)

        //to call onDraw method
        invalidate()

        return true

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        circleRadius = (min(width, height) / 2.0 * 0.8).toFloat()
    }

    //extension function of PointF to find x and y positions on screen
    private fun PointF.computeXYForSpeed(pos: FanSpeed, radius: Float) {
        val startAngle = Math.PI * (9 / 8.0)
        val angle = startAngle + pos.ordinal * (Math.PI / 4)
        x = (radius * cos(angle)).toFloat() + width / 2
        y = (radius * sin(angle)).toFloat() + height / 2
    }

    //which will call every time when screen refreshes.
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = when (fanSpeed) {
            FanSpeed.OFF -> Color.GRAY
            FanSpeed.LOW -> fanSpeedLowColor
            FanSpeed.MEDIUM -> fanSpeedMediumColor
            FanSpeed.HIGH -> fanSpeedMaxColor
        }

        //draw circle using canvas
        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), circleRadius, paint)

        //Draw indicator circle
        val markerRadius = circleRadius + RADIUS_OFFSET_INDICATOR
        pointPosition.computeXYForSpeed(fanSpeed, markerRadius)
        paint.color = Color.BLACK
        canvas?.drawCircle(pointPosition.x, pointPosition.y, circleRadius / 12, paint)

        //Draw the labels
        val labelRadius = circleRadius + RADIUS_OFFSET_LABEL
        for (i in FanSpeed.values()) {
            pointPosition.computeXYForSpeed(i, labelRadius)
            val label = resources.getString(i.label)
            canvas?.drawText(label, pointPosition.x, pointPosition.y, paint)
        }

    }

}


