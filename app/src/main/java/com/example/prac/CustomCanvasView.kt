package com.example.prac

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomCanvasView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val lines: MutableList<Triple<Pair<Float, Float>, Pair<Float, Float>, Int>> = mutableListOf()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Рисуем координатные оси
        val paint = Paint().apply {
            strokeWidth = 2f
            color = Color.BLACK
        }
        val centerX = width / 2f
        val centerY = height / 2f

        // Горизонтальная ось (X)
        canvas.drawLine(0f, centerY, width.toFloat(), centerY, paint)

        // Вертикальная ось (Y)
        canvas.drawLine(centerX, 0f, centerX, height.toFloat(), paint)

        // Вертикальное пересечение (линия через центр по оси X)
        canvas.drawLine(centerX, centerY - 50f, centerX, centerY + 50f, paint)

        // Добавляем метки на пересечении осей
        paint.textSize = 24f
        canvas.drawText("X", width - 30f, centerY + 30f, paint)
        canvas.drawText("Y", centerX + 10f, 30f, paint)

        // Рисуем линии
        val linePaint = Paint().apply {
            strokeWidth = 5f
        }
        for (line in lines) {
            linePaint.color = line.third
            val startX = centerX + line.first.first
            val startY = centerY - line.first.second
            val endX = centerX + line.second.first
            val endY = centerY - line.second.second
            canvas.drawLine(startX, startY, endX, endY, linePaint)
        }
    }

    fun addLine(start: Pair<Float, Float>, end: Pair<Float, Float>, color: Int) {
        lines.add(Triple(start, end, color))
        invalidate()
    }

    fun clearCanvas() {
        lines.clear()
        invalidate()
    }
}
