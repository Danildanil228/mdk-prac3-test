package com.example.prac

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AlertDialog

class MainActivity : ComponentActivity() {

    private lateinit var tvCoordinates: TextView
    private lateinit var canvasView: CustomCanvasView
    private var selectedColorId: Int = R.id.rbGreen

    // Переменные для хранения координат и цвета
    private var x1: Float = 0f
    private var y1: Float = 0f
    private var x2: Float = 0f
    private var y2: Float = 0f
    private var color: Int = Color.GREEN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCoordinates = findViewById(R.id.tvCoordinates)
        canvasView = findViewById(R.id.canvasView)

        val btnInput: Button = findViewById(R.id.btnInput)
        val btnDraw: Button = findViewById(R.id.btnDraw)
        val btnClear: Button = findViewById(R.id.btnClear)

        btnInput.setOnClickListener {
            showInputDialog()
        }

        btnDraw.setOnClickListener {
            drawLine()
        }

        btnClear.setOnClickListener {
            clearCanvas()
        }
    }

    private fun showInputDialog() {
        val alertDialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.dialog_input, null)
        alertDialog.setView(view)

        val etX1: EditText = view.findViewById(R.id.etX1)
        val etY1: EditText = view.findViewById(R.id.etY1)
        val etX2: EditText = view.findViewById(R.id.etX2)
        val etY2: EditText = view.findViewById(R.id.etY2)

        val rbGreen: RadioButton = view.findViewById(R.id.rbGreen)
        val rbRed: RadioButton = view.findViewById(R.id.rbRed)
        val rbBlue: RadioButton = view.findViewById(R.id.rbBlue)

        rbGreen.isChecked = true
        rbGreen.setOnClickListener { selectedColorId = R.id.rbGreen }
        rbRed.setOnClickListener { selectedColorId = R.id.rbRed }
        rbBlue.setOnClickListener { selectedColorId = R.id.rbBlue }

        alertDialog.setPositiveButton("OK") { _, _ ->
            x1 = etX1.text.toString().toFloatOrNull() ?: 0f
            y1 = etY1.text.toString().toFloatOrNull() ?: 0f
            x2 = etX2.text.toString().toFloatOrNull() ?: 0f
            y2 = etY2.text.toString().toFloatOrNull() ?: 0f

            if (x1 == 0f || y1 == 0f || x2 == 0f || y2 == 0f) {
                Toast.makeText(this, "Пожалуйста, введите корректные координаты", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            color = when (selectedColorId) {
                R.id.rbRed -> Color.RED
                R.id.rbBlue -> Color.BLUE
                else -> Color.GREEN
            }

            tvCoordinates.text = "Coordinates: ($x1, $y1) to ($x2, $y2)"
        }
        alertDialog.setNegativeButton("Cancel", null)
        alertDialog.create().show()
    }

    private fun drawLine() {
        canvasView.addLine(Pair(x1, y1), Pair(x2, y2), color)
    }

    private fun clearCanvas() {
        tvCoordinates.text = "Coordinates"
        canvasView.clearCanvas()
    }
}
