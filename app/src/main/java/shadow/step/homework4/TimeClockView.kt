package shadow.step.homework4

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

class TimeClockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {

    private companion object {
        private const val RADIUS = 400f
        var heightV = 0
        var widthV = 0
        var padding = 0
        var handTruncation = 0
        var hourHandTruncation = 0
        var radius = 0
        var isInit: Boolean = false
        var colorStatusHour = 1
        var colorStatusMin = 1
        var colorStatusSec = 1
        var sizeHandsStatus = 1
    }

    private val paintBackground = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.FILL
        isAntiAlias = true
        strokeWidth = 30f
    }
    private val paintMarkupCircle = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeWidth = 20f
    }
    private val paintMarkup = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        isAntiAlias = true
        strokeWidth = 30f
    }
    private val paintHandHour = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeWidth = 20f
    }
    private val paintHandMinutes = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeWidth = 25f
    }
    private val paintHandSeconds = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeWidth = 15f
    }
    private val timeMarkup: Rect = Rect()

    fun setSizeHands() {
        when (sizeHandsStatus) {
            1 -> {
                paintHandSeconds.strokeWidth = 15f
                paintHandMinutes.strokeWidth = 15f
                paintHandHour.strokeWidth = 15f
            }
            2 -> {
                paintHandSeconds.strokeWidth = 20f
                paintHandMinutes.strokeWidth = 20f
                paintHandHour.strokeWidth = 20f
            }
            3 -> {
                paintHandSeconds.strokeWidth = 25f
                paintHandMinutes.strokeWidth = 25f
                paintHandHour.strokeWidth = 25f
            }
            4 -> {
                paintHandSeconds.strokeWidth = 30f
                paintHandMinutes.strokeWidth = 30f
                paintHandHour.strokeWidth = 30f
            }
        }
        if (sizeHandsStatus < 4) {
            sizeHandsStatus++
        } else {
            sizeHandsStatus = 1
        }
    }

    fun setColorHandSecondsTheme() {
        when (colorStatusSec) {
            1 -> paintHandSeconds.color = Color.YELLOW
            2 -> paintHandSeconds.color = Color.BLUE
            3 -> paintHandSeconds.color = Color.GREEN
            4 -> paintHandSeconds.color = Color.BLACK
            5 -> paintHandSeconds.color = Color.RED
            6 -> paintHandSeconds.color = Color.CYAN
        }
        if (colorStatusSec < 6) {
            colorStatusSec++
        } else {
            colorStatusSec = 1
        }
    }

    fun setColorHandMinutesTheme() {
        when (colorStatusMin) {
            1 -> paintHandMinutes.color = Color.YELLOW
            2 -> paintHandMinutes.color = Color.BLUE
            3 -> paintHandMinutes.color = Color.GREEN
            4 -> paintHandMinutes.color = Color.BLACK
            5 -> paintHandMinutes.color = Color.RED
            6 -> paintHandMinutes.color = Color.CYAN
        }
        if (colorStatusMin < 6) {
            colorStatusMin++
        } else {
            colorStatusMin = 1
        }
    }

    fun setColorHandHoursTheme() {
        when (colorStatusHour) {
            1 -> paintHandHour.color = Color.YELLOW
            2 -> paintHandHour.color = Color.BLUE
            3 -> paintHandHour.color = Color.GREEN
            4 -> paintHandHour.color = Color.BLACK
            5 -> paintHandHour.color = Color.RED
            6 -> paintHandHour.color = Color.CYAN
        }
        if (colorStatusHour < 6) {
            colorStatusHour++
        } else {
            colorStatusHour = 1
        }
    }

    override fun onDraw(canvas: Canvas) {
        if (!isInit) {
            initClock()
        }
        drawClocksMarkup(canvas)
        drawClockHands(canvas)

        postInvalidateDelayed(1000)
        invalidate()
    }

    private fun drawClocksMarkup(canvas: Canvas) {
        canvas.apply {
            val centerX = (width / 2).toFloat()
            val centerY = (height / 2).toFloat()
            drawColor(Color.WHITE)
            drawCircle(centerX, centerY, RADIUS, paintBackground)
            drawCircle(centerX, centerY, RADIUS, paintMarkupCircle)
        }
        for (n in 1..12) {
            val rotate = 30f
            timeMarkup.apply {
                left = (width / 2 - RADIUS.toInt())
                right = (width / 2 - (RADIUS - 50).toInt())
                top = (height / 2 - 10)
                bottom = (height / 2 + 10)
                canvas.rotate(rotate, width / 2f, height / 2f)
                canvas.drawRect(timeMarkup, paintMarkup)
            }
        }
    }

    private fun initClock() {
        heightV = height
        widthV = width
        val min = Math.min(heightV, widthV)
        radius = min / 2 - padding
        handTruncation = min / 20
        hourHandTruncation = min / 7
        isInit = true
    }

    private fun drawHandHour(canvas: Canvas, loc: Double) {
        val angle = Math.PI * loc / 30 - Math.PI / 2
        val handRadius = RADIUS - RADIUS / 2.5f

        canvas.drawLine(
            widthV / 2f, heightV / 2f,
            ((widthV / 2f + cos(angle) * handRadius).toFloat()),
            ((heightV / 2f + sin(angle) * handRadius).toFloat()),
            paintHandHour
        )
    }

    private fun drawHandMinutes(canvas: Canvas, loc: Double) {
        val angle = Math.PI * loc / 30 - Math.PI / 2
        val handRadius = RADIUS - RADIUS / 4f

        canvas.drawLine(
            widthV / 2f, heightV / 2f,
            ((widthV / 2f + cos(angle) * handRadius).toFloat()),
            ((heightV / 2f + sin(angle) * handRadius).toFloat()),
            paintHandMinutes
        )
    }

    private fun drawHandSeconds(canvas: Canvas, loc: Double) {
        val angle = Math.PI * loc / 30 - Math.PI / 2
        val handRadius = RADIUS - RADIUS / 2f

        canvas.drawLine(
            widthV / 2f, heightV / 2f,
            ((widthV / 2f + cos(angle) * handRadius).toFloat()),
            ((heightV / 2f + sin(angle) * handRadius).toFloat()),
            paintHandSeconds
        )
    }

    private fun drawClockHands(canvas: Canvas) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        if (hour > 12) {
            hour - 12
        }
        drawHandHour(canvas, ((hour + calendar.get(Calendar.MINUTE) / 60) * 5f).toDouble())
        drawHandMinutes(canvas, calendar.get(Calendar.MINUTE).toDouble())
        drawHandSeconds(canvas, calendar.get(Calendar.SECOND).toDouble())
    }

}
