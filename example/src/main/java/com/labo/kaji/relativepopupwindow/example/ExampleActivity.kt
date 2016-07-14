package com.labo.kaji.relativepopupwindow.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.labo.kaji.relativepopupwindow.RelativePopupWindow.HorizontalPosition
import com.labo.kaji.relativepopupwindow.RelativePopupWindow.VerticalPosition
import kotlinx.android.synthetic.main.activity_example.*

class ExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)

        spinner_vertical.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item).apply {
            addAll(*resources.getStringArray(R.array.vertical_positions))
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        spinner_horizontal.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item).apply {
            addAll(*resources.getStringArray(R.array.horizontal_positions))
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        button1.setOnClickListener { view ->
            val vertPos = when (spinner_vertical.selectedItemPosition) {
                0 -> VerticalPosition.ABOVE
                1 -> VerticalPosition.ALIGN_BOTTOM
                2 -> VerticalPosition.CENTER
                3 -> VerticalPosition.ALIGN_TOP
                else -> VerticalPosition.BELOW
            }
            val horizPos = when (spinner_horizontal.selectedItemPosition) {
                0 -> HorizontalPosition.LEFT
                1 -> HorizontalPosition.ALIGN_RIGHT
                2 -> HorizontalPosition.CENTER
                3 -> HorizontalPosition.ALIGN_LEFT
                else -> HorizontalPosition.RIGHT
            }
            ExampleCardPopup(view.context).showOnAnchor(view, vertPos, horizPos)
        }
    }

}
